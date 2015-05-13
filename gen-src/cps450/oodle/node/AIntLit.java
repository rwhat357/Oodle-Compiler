/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AIntLit extends PLit
{
    private TIntLit _e_;

    public AIntLit()
    {
        // Constructor
    }

    public AIntLit(
        @SuppressWarnings("hiding") TIntLit _e_)
    {
        // Constructor
        setE(_e_);

    }

    @Override
    public Object clone()
    {
        return new AIntLit(
            cloneNode(this._e_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntLit(this);
    }

    public TIntLit getE()
    {
        return this._e_;
    }

    public void setE(TIntLit node)
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
            setE((TIntLit) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}