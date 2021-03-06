/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class ANotExpr6 extends PExpr6
{
    private TNot _not_;
    private PExpr9 _e_;

    public ANotExpr6()
    {
        // Constructor
    }

    public ANotExpr6(
        @SuppressWarnings("hiding") TNot _not_,
        @SuppressWarnings("hiding") PExpr9 _e_)
    {
        // Constructor
        setNot(_not_);

        setE(_e_);

    }

    @Override
    public Object clone()
    {
        return new ANotExpr6(
            cloneNode(this._not_),
            cloneNode(this._e_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotExpr6(this);
    }

    public TNot getNot()
    {
        return this._not_;
    }

    public void setNot(TNot node)
    {
        if(this._not_ != null)
        {
            this._not_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._not_ = node;
    }

    public PExpr9 getE()
    {
        return this._e_;
    }

    public void setE(PExpr9 node)
    {
        if(this._e_ != null)
        {
            this._e_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._e_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._not_)
            + toString(this._e_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._not_ == child)
        {
            this._not_ = null;
            return;
        }

        if(this._e_ == child)
        {
            this._e_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._not_ == oldChild)
        {
            setNot((TNot) newChild);
            return;
        }

        if(this._e_ == oldChild)
        {
            setE((PExpr9) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
