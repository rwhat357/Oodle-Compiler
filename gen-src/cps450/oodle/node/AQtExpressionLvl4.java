/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AQtExpressionLvl4 extends PExpressionLvl4
{
    private PExpressionLvl4 _expressionLvl4_;
    private TGreaterThan _greaterThan_;
    private PExpressionLvl41 _expressionLvl41_;

    public AQtExpressionLvl4()
    {
        // Constructor
    }

    public AQtExpressionLvl4(
        @SuppressWarnings("hiding") PExpressionLvl4 _expressionLvl4_,
        @SuppressWarnings("hiding") TGreaterThan _greaterThan_,
        @SuppressWarnings("hiding") PExpressionLvl41 _expressionLvl41_)
    {
        // Constructor
        setExpressionLvl4(_expressionLvl4_);

        setGreaterThan(_greaterThan_);

        setExpressionLvl41(_expressionLvl41_);

    }

    @Override
    public Object clone()
    {
        return new AQtExpressionLvl4(
            cloneNode(this._expressionLvl4_),
            cloneNode(this._greaterThan_),
            cloneNode(this._expressionLvl41_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAQtExpressionLvl4(this);
    }

    public PExpressionLvl4 getExpressionLvl4()
    {
        return this._expressionLvl4_;
    }

    public void setExpressionLvl4(PExpressionLvl4 node)
    {
        if(this._expressionLvl4_ != null)
        {
            this._expressionLvl4_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expressionLvl4_ = node;
    }

    public TGreaterThan getGreaterThan()
    {
        return this._greaterThan_;
    }

    public void setGreaterThan(TGreaterThan node)
    {
        if(this._greaterThan_ != null)
        {
            this._greaterThan_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._greaterThan_ = node;
    }

    public PExpressionLvl41 getExpressionLvl41()
    {
        return this._expressionLvl41_;
    }

    public void setExpressionLvl41(PExpressionLvl41 node)
    {
        if(this._expressionLvl41_ != null)
        {
            this._expressionLvl41_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expressionLvl41_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expressionLvl4_)
            + toString(this._greaterThan_)
            + toString(this._expressionLvl41_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expressionLvl4_ == child)
        {
            this._expressionLvl4_ = null;
            return;
        }

        if(this._greaterThan_ == child)
        {
            this._greaterThan_ = null;
            return;
        }

        if(this._expressionLvl41_ == child)
        {
            this._expressionLvl41_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expressionLvl4_ == oldChild)
        {
            setExpressionLvl4((PExpressionLvl4) newChild);
            return;
        }

        if(this._greaterThan_ == oldChild)
        {
            setGreaterThan((TGreaterThan) newChild);
            return;
        }

        if(this._expressionLvl41_ == oldChild)
        {
            setExpressionLvl41((PExpressionLvl41) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
