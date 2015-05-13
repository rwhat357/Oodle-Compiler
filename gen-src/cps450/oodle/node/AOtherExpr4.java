/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AOtherExpr4 extends PExpr4
{
    private PExpr5 _e_;

    public AOtherExpr4()
    {
        // Constructor
    }

    public AOtherExpr4(
        @SuppressWarnings("hiding") PExpr5 _e_)
    {
        // Constructor
        setE(_e_);

    }

    @Override
    public Object clone()
    {
        return new AOtherExpr4(
            cloneNode(this._e_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOtherExpr4(this);
    }

    public PExpr5 getE()
    {
        return this._e_;
    }

    public void setE(PExpr5 node)
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
            + toString(this._e_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
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
        if(this._e_ == oldChild)
        {
            setE((PExpr5) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}