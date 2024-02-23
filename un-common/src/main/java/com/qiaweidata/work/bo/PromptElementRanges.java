package com.qiaweidata.work.bo;

public class PromptElementRanges {
    String kind;
    Integer start;
    Integer end;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PromptElementRanges)) return false;
        PromptElementRanges other = (PromptElementRanges) o;
        if (!other.canEqual(this)) return false;
        Object this$start = getStart(), other$start = other.getStart();
        if ((this$start == null) ? (other$start != null) : !this$start.equals(other$start)) return false;
        Object this$end = getEnd(), other$end = other.getEnd();
        if ((this$end == null) ? (other$end != null) : !this$end.equals(other$end)) return false;
        Object this$kind = getKind(), other$kind = other.getKind();
        return !((this$kind == null) ? (other$kind != null) : !this$kind.equals(other$kind));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PromptElementRanges;
    }


    public String toString() {
        return "PromptElementRanges(kind=" + getKind() + ", start=" + getStart() + ", end=" + getEnd() + ")";
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return this.end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}