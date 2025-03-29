package com.fly.git.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import com.fly.git.util.SendEmail;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.filter.PathFilterGroup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fly.core.exception.ValidateException;
import com.fly.core.utils.HttpRequestUtils;
import com.fly.git.model.GitInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * GitClientService
 * 
 * @author 00fly
 * @version [版本号, 2021年6月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Service
public class GitClientService
{
    private GitInfo gitInfo;
    
    private String localPath;
    
    private UsernamePasswordCredentialsProvider credentialsProvider;
    
    private Set<Integer> runHours = new ConcurrentSkipListSet<>();
    
    private Long lastRunTime;
    
    // 上次提交的内容
    String commitText;

    public Git git;

     public static final Map<String, String> properties = new HashMap<>(2);
    
    public GitClientService()
    {
        super();
        try
        {
            ResourceBundle rb2 = ResourceBundle.getBundle("git");
            for(String key : rb2.keySet()){
                String value = rb2.getString(key);
                properties.put(key, value);
            }
            localPath = new File(properties.get("project")).getCanonicalPath();

        }
        catch (IOException e)
        {
        }
    }
    
    /**
     * 初始化
     * 
     * @see [类、类#方法、类#成员]
     */
    @PostConstruct
    public void init()
    {
        File file = new File(properties.get("default.ser.path"));
        if (!file.exists())
        {
            log.error("####### init file [{}] not exist!", file.getAbsolutePath());
            return;
        }
        // 反序列化
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            gitInfo = (GitInfo)ois.readObject();
            Assert.notNull(gitInfo, "gitInfo is null");
            Assert.isTrue(StringUtils.isNoneEmpty(gitInfo.getUsername(), gitInfo.getPassword(), gitInfo.getEmail(), gitInfo.getRemoteGit()), "git params is empty");
            
            credentialsProvider = new UsernamePasswordCredentialsProvider(gitInfo.getUsername(), gitInfo.getPassword());
            log.info("****** init from file [{}] success!", file.getAbsolutePath());
            registerHours();
        }
        catch (IOException | ClassNotFoundException e)
        {
            log.error("###### init failure! {}", e.getMessage());
        }
    }
    
    /**
     * 克隆远程库
     * 
     * @throws InvalidRemoteException
     * @throws TransportException
     * @throws GitAPIException
     * @see [类、类#方法、类#成员]
     */
    public void gitClone()
        throws InvalidRemoteException, TransportException, GitAPIException
    {
        Assert.notNull(gitInfo, "gitInfo is null");
        CloneCommand cloneCommand = Git.cloneRepository();
        // CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
        //         "PRIVATE-TOKEN",
        //         "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"
        //     );
        cloneCommand.setURI(gitInfo.getRemoteGit()).setBranch("main")
            // .setCredentialsProvider(credentialsProvider)
        .setCredentialsProvider(credentialsProvider);
        cloneCommand.setDirectory(new File(localPath));
        try (Git git = cloneCommand.call())
        {
            log.info("★★★★ gitClone success!  tag: {}", git.tag());
        } catch (Exception e) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {

            }
            gitClone();
        }
    }
    
    /**
     * 本地提交代码
     */
    public void localCommit()
        throws IOException, GitAPIException, JGitInternalException
    {
        Assert.notNull(gitInfo, "gitInfo is null");
        try (Git git = new Git(new FileRepository(localPath + "/.git")))
        {
            String remark = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + " auto commit.";
            git.commit().setAmend(true).setAuthor(gitInfo.getUsername(), gitInfo.getEmail()).setMessage(remark).call();
        }
    }
    
    /**
     * 拉取远程仓库内容到本地
     */
    public void remotePull()
        throws IOException, GitAPIException
    {
        Assert.notNull(gitInfo, "gitInfo is null");
        try (Git git = new Git(new FileRepository(localPath + "/.git")))
        {
            CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
                "PRIVATE-TOKEN",
                "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"
            );
            git.pull().setRemote("origin").setRemoteBranchName("main").setCredentialsProvider(credentialsProvider).call();
            log.info("远程更新成功");
        } catch (Exception e) {
            log.error("远程更新失败", e);
            if (e.getMessage().indexOf("connection failed") != -1) {
                log.error("递归执行更新");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {

                }
                remotePull();
            }

        }
    }
    
    /**
     * push本地代码到远程仓库地址
     */
    public void remotePush()
        throws IOException, JGitInternalException, GitAPIException
    {
        Assert.notNull(gitInfo, "gitInfo is null");
        try (Git git = new Git(new FileRepository(localPath + "/.git")))
        {
            git.push().setRemote("origin").setCredentialsProvider(credentialsProvider).call();
        }
    }
    
    /**
     * 定时任务调度逻辑
     * 
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void schedule()
    {

        if (true) {
            return;
        }
        int curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (runHours.isEmpty() || curHour == 0)
        {
            registerHours();
        }
        log.info("---- runHours is {} ----", runHours);
        
        // 到达设定时间点或当天未提交过
        String date = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd");
        if (runHours.contains(curHour) || !StringUtils.startsWith(commitText, date))
        {
            try
            {
                // 删除project目录
                if (SystemUtils.IS_OS_UNIX)
                {
                    Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "rm -rf " + localPath});
                }
                else if (SystemUtils.IS_OS_WINDOWS)
                {
                    Runtime.getRuntime().exec("cmd /c start " + localPath);
                    TimeUnit.SECONDS.sleep(1);
                    Runtime.getRuntime().exec("cmd /c rd /s/q " + localPath);
                }
                long seconds = RandomUtils.nextLong(10, 100L);
                log.info("★★★★ thread will sleep seconds： {}", seconds);
                TimeUnit.SECONDS.sleep(seconds);
                runGitAll();
            }
            catch (InterruptedException | IOException | GitAPIException e)
            {
                log.error("schedule error", e.getCause());
            }
        }
    }
    
    /**
     * registerRunHours
     * 
     * @see [类、类#方法、类#成员]
     */
    public void registerHours()
    {
        runHours.clear();
        int curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        IntStream.range(curHour, 24).filter(n -> RandomUtils.nextInt(0, 10) > 6).forEach(runHours::add);
        log.info("---- runHours init success: {} ----", runHours);
    }
    
    /**
     * 全流程
     * 
     * @throws IOException
     * @throws GitAPIException
     * @throws InterruptedException
     * 
     * @see [类、类#方法、类#成员]
     */
    public void runGitAll()
        throws IOException, GitAPIException
    {
        Assert.notNull(gitInfo, "gitInfo is null");
        
        // 仅限制http接口请求，不影响定时任务
        if (HttpRequestUtils.getHttpServletRequest() != null)
        {
            if (null != null && System.currentTimeMillis() < lastRunTime + 600000)
            {
                String msg = String.format("您请求过于频繁，请%s后再试", DateFormatUtils.format(lastRunTime + 600000, "HH:mm:ss"));
                throw new ValidateException(msg);
            }
            lastRunTime = System.currentTimeMillis();
        }
        log.info("★★★★ it is time to ready run ......");
        if (!new File(localPath).exists())
        {
            gitClone();
        } else {
            remotePull();
        }
        
        // 替换最后一行内容
        File readMe = new File("project/README.md");
        List<String> lines = FileUtils.readLines(readMe, StandardCharsets.UTF_8.toString());
        if (lines.size() > 1)
        {
            //lines.remove(lines.size() - 1);
        }
        commitText = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        lines.add(commitText);
        FileUtils.writeLines(readMe, StandardCharsets.UTF_8.toString(), lines);
        
        File rootDir = new File(properties.get("project"));
        try (Git git = Git.open(rootDir))
        {

            List<String> files = Arrays.asList(rootDir.list(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String name)
                {
                    return !name.endsWith(".git");
                }
            }));
            List<DiffEntry> diffEntries = git.diff().setPathFilter(PathFilterGroup.createFromStrings(files)).setShowNameAndStatusOnly(true).call();
            if (diffEntries == null || diffEntries.isEmpty())
            {
                return;
            }
            // 被修改过的文件
            List<String> updateFiles = new ArrayList<String>();
            ChangeType changeType;
            for (DiffEntry entry : diffEntries)
            {
                changeType = entry.getChangeType();
                switch (changeType)
                {
                    case ADD:
                    case COPY:
                    case RENAME:
                    case MODIFY:
                        updateFiles.add(entry.getNewPath());
                        break;
                    case DELETE:
                        updateFiles.add(entry.getOldPath());
                        break;
                }
            }
            
            // 将文件提交到git仓库中，并返回本次提交的版本号
            // 1、将工作区的内容更新到暂存区
            AddCommand addCmd = git.add();
            for (String file : updateFiles)
            {
                addCmd.addFilepattern(file);
            }
            addCmd.call();
            
            // 2、commit
            CommitCommand commitCmd = git.commit();
            for (String file : updateFiles)
            {
                commitCmd.setOnly(file);
            }
            RevCommit revCommit = commitCmd.setCommitter(gitInfo.getUsername(), gitInfo.getEmail()).setMessage(commitText + " Cache File Analysis.").call();
            log.info("★★★★ local commit successful:{}", revCommit.getName());
            
            // swagger测试忽略远程提交
            if (HttpRequestUtils.getHttpServletRequest() != null)
            {
                //log.info("★★★★ Swagger HttpServletRequest Test, not call remote git push ★★★★ ");
                //return;
            }

            // 3、git push
            log.info("★★★★ now call remote git push ★★★★");
            PushCommand push = git.push();
            push.setRemote("origin").setCredentialsProvider(credentialsProvider).call();
            log.info("★★★★ remote git push successful ★★★★\n");
        }
    }
    
    /**
     * autoCommitPull
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @Scheduled(cron = "0 0 9-17 * * ?")
    public boolean autoCommitPull()
    {
        try
        {
            log.info("★★★★ autoCommitPull ★★★★");
            if (!new File(localPath).exists())
            {
                gitClone();
            }
            localCommit();
            remotePush();
            return true;
        }
        catch (JGitInternalException | GitAPIException | IOException e)
        {
            log.error("autoCommitPull error", e.getCause());
            return false;
        }
    }
    
    public Set<Integer> getRunHours()
    {
        return runHours;
    }

    public static void main(String[] args) throws GitAPIException, IOException, InterruptedException {

        GitClientService gitClientService = new GitClientService();
        gitClientService.initShen();

        // gitClientService.gitClone();

        gitClientService.runUpdate();

        gitClientService.tempGitCommit();

        //gitClientService.runRomevt();
    }

    public void tempGitCommit() {

        try (Git git = new Git(new FileRepository(localPath + "/.git")))
        {
            this.git = git;
            StringBuilder sb = new StringBuilder();
            runCommit(sb);

//            SendEmail.sendEmail(sb);
        } catch (Exception e) {
            log.error("git 错误|{}", e.getMessage());
        }
    }

    public void runUpdate() {
        try (Git git = new Git(new FileRepository(localPath + "/.git")))
        {
            CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
                "PRIVATE-TOKEN",
                "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"
            );
            git.pull()
                .setRemote("origin")
                .setRemoteBranchName("main")
                .setCredentialsProvider(credentialsProvider)
                .call();
            this.git = git;
            log.info("更新成功");
        } catch (Exception e) {
            log.error("重新执行更新|{}", e.getMessage());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            runUpdate();
        }
    }

    private void runCommit(StringBuilder sb) throws GitAPIException, IOException, InterruptedException {

        // 替换最后一行内容
        // File readMe = new File("project/README.md");
        // List<String> lines = FileUtils.readLines(readMe, StandardCharsets.UTF_8.toString());
        // commitText = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        // lines.add(commitText);
        // FileUtils.writeLines(readMe, StandardCharsets.UTF_8.toString(), lines);
        File rootDir = new File(properties.get("project"));
        List<String> files = Arrays.asList(rootDir.list(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String name)
                {
                    return !name.endsWith(".git");
                }
            }));
        List<DiffEntry> diffEntries = git.diff().setPathFilter(PathFilterGroup.createFromStrings(files)).setShowNameAndStatusOnly(true).call();
        if (diffEntries == null ||
            diffEntries.isEmpty()) {
            return;
        }

        // 被修改过的文件
        List<String> updateFiles = new ArrayList<String>();
        ChangeType changeType;
        for (DiffEntry entry : diffEntries) {
            changeType = entry.getChangeType();
            switch (changeType) {
                case ADD:
                case COPY:
                case RENAME:
                case MODIFY:
                    updateFiles.add(entry.getNewPath());
                    break;
                case DELETE:
                    updateFiles.add(entry.getOldPath());
                    break;
            }
        }

        // 1、将工作区的内容更新到暂存区
        AddCommand addCmd = git.add();
        for (String file : updateFiles) {
            addCmd.addFilepattern(file);
        }
        addCmd.call();

        // 2、commit
        CommitCommand commitCmd = git.commit();

        for (String file : updateFiles) {
            commitCmd.setOnly(file);
            sb.append(file).append(" / ");
        }
        CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
                "PRIVATE-TOKEN",
                "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"
            );
        commitCmd.setCredentialsProvider(credentialsProvider);
        RevCommit revCommit = commitCmd/*.setCommitter(gitInfo.getUsername(), gitInfo.getEmail())*/
            .setMessage(sb + " file update.")
            .call();
        log.info("★★★★ local commit successful:{}", revCommit.getName());

        // 3、git push
        log.info("★★★★ now call remote git push ★★★★");
        runRomevt();
        log.info("★★★★ remote git push successful ★★★★\n");

    }

    private void runRomevt() throws InterruptedException {

        try (Git git = new Git(new FileRepository(localPath + "/.git"))) {
            PushCommand push = git.push();
            CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
                "PRIVATE-TOKEN",
                "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"
            );
            push/*.setRemote("origin")*/.setForce(true).setCredentialsProvider(credentialsProvider).call();
        } catch (GitAPIException e) {
            log.error("没提交成功|{}", e.getMessage());
            TimeUnit.SECONDS.sleep(5);
            runRomevt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initShen()
    {
        File file = new File(properties.get("default.ser.path"));
        if (!file.exists())
        {
            log.error("####### init file [{}] not exist!", file.getAbsolutePath());
            return;
        }
        // 反序列化
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            gitInfo = (GitInfo)ois.readObject();
            Assert.notNull(gitInfo, "gitInfo is null");
            Assert.isTrue(StringUtils.isNoneEmpty(gitInfo.getUsername(), gitInfo.getPassword(), gitInfo.getEmail(), gitInfo.getRemoteGit()), "git params is empty");

            credentialsProvider = new UsernamePasswordCredentialsProvider(gitInfo.getUsername(), gitInfo.getPassword());
            log.info("****** init from file [{}] success!", file.getAbsolutePath());
            registerHours();
        }
        catch (IOException | ClassNotFoundException e)
        {
            log.error("###### init failure! {}", e.getMessage());
        }
    }
}
