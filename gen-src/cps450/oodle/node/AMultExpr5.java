/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AMultExpr5 extends PExpr5
{
    private PExpr5 _starts_;
    private TOpMult _opMult_;
    private PExpr6 _ends_;

    public AMultExpr5()
    {
        // Constructor
    }

    public AMultExpr5(
        @SuppressWarnings("hiding") PExpr5 _starts_,
        @SuppressWarnings("hiding") TOpMult _opMult_,
        @SuppressWarnings("hiding") PExpr6 _ends_)
    {
        // Constructor
        setStarts(_starts_);

        setOpMult(_opMult_);

        setEnds(_ends_);

    }

    @Override
    public Object clone()
    {
        return new AMultExpr5(
            cloneNode(this._starts_),
            cloneNode(this._opMult_),
            cloneNode(this._ends_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultExpr5(this);
    }

    public PExpr5 getStarts()
    {
        return this._starts_;
    }

    public void setStarts(PExpr5 node)
    {
        if(this._starts_ != null)
        {
            this._starts_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._starts_ = node;
    }

    public TOpMult getOpMult()
    {
        return this._opMult_;
    }

    public void setOpMult(TOpMult node)
    {
        if(this._opMult_ != null)
        {
            this._opMult_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._opMult_ = node;
    }

    public PExpr6 getEnds()
    {
        return this._ends_;
    }

    public void setEnds(PExpr6 node)
    {
        if(this._ends_ != null)
        {
            this._ends_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ends_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._starts_)
            + toString(this._opMult_)
            + toString(this._ends_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._starts_ == child)
        {
            this._starts_ = null;
            return;
        }

        if(this._opMult_ == child)
        {
            this._opMult_ = null;
            return;
        }

        if(this._ends_ == child)
        {
            this._ends_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._starts_ == oldChild)
        {
            setStarts((PExpr5) newChild);
            return;
        }

        if(this._opMult_ == oldChild)
        {
            setOpMult((TOpMult) newChild);
            return;
        }

        if(this._ends_ == oldChild)
        {
            setEnds((PExpr6) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
