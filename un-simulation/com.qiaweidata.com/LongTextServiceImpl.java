package com.fly.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fly.common.DaoException;
import com.fly.common.PaginationSupport;
import com.fly.common.ServiceException;
import com.fly.core.TransactionProvider;
import com.fly.demo.dao.LongTextDAO;
import com.fly.demo.dao.impl.LongTextDAOImpl;
import com.fly.demo.entity.LongText;
import com.fly.demo.service.LongTextService;

/**
 * 
 * LongTextService 接口实现类
 * 
 * @author 00fly
 * @version [版本号, 2018-09-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LongTextServiceImpl extends TransactionProvider implements LongTextService
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LongTextServiceImpl.class);
    
    private LongTextDAO longTextDAO = LongTextDAOImpl.getInstance();
    
    private LongTextServiceImpl()
    {
        super();
    }
    
    private static final LongTextServiceImpl SINGLE = new LongTextServiceImpl();
    
    /**
     * 单例获取对象
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static LongTextServiceImpl getInstance()
    {
        return SINGLE;
    }
    
    @Override
    public void insert(LongText longText)
        throws ServiceException
    {
        try
        {
            longTextDAO.insert(longText);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public void deleteById(String id)
        throws ServiceException
    {
        try
        {
            longTextDAO.deleteById(id);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public long deleteById(String[] ids)
        throws ServiceException
    {
        try
        {
            return longTextDAO.deleteById(ids);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public long deleteById(List<String> ids)
        throws ServiceException
    {
        try
        {
            return longTextDAO.deleteById(ids);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public void update(LongText longText)
        throws ServiceException
    {
        try
        {
            longTextDAO.updateById(longText);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public void saveOrUpdate(LongText longText)
        throws ServiceException
    {
        try
        {
            if (longText.getId() != null && StringUtils.isNotBlank(Objects.toString(longText.getId())))
            {
                longTextDAO.updateById(longText);
            }
            else
            {
                longTextDAO.insert(longText);
            }
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public LongText queryById(String id)
        throws ServiceException
    {
        try
        {
            return longTextDAO.queryById(id);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public List<LongText> queryAll()
        throws ServiceException
    {
        try
        {
            return longTextDAO.queryAll();
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 根据条件分页查询
     * 
     * @param longText 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     * @throws ServiceException
     */
    @Override
    public PaginationSupport<LongText> queryForPagination(LongText longText, int pageNo, int pageSize)
        throws ServiceException
    {
        try
        {
            return longTextDAO.queryForPagination(longText, pageNo, pageSize);
        }
        catch (DaoException e)
        {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 事务方法
     * 
     * @throws ServiceException
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void testTrans()
        throws ServiceException
    {
        try
        {
            startTransaction();
            List<LongText> list = queryAll();
            for (LongText longText : list)
            {
                longTextDAO.insert(longText);
            }
            int i = 1 / 0; // 抛出异常
            Assert.assertNull(i);
            commitAndClose();
        }
        catch (Exception e)
        {
            rollbackAndClose();
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
