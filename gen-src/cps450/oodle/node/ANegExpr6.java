/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class ANegExpr6 extends PExpr6
{
    private TOpMinus _opMinus_;
    private PExpr9 _e_;

    public ANegExpr6()
    {
        // Constructor
    }

    public ANegExpr6(
        @SuppressWarnings("hiding") TOpMinus _opMinus_,
        @SuppressWarnings("hiding") PExpr9 _e_)
    {
        // Constructor
        setOpMinus(_opMinus_);

        setE(_e_);

    }

    @Override
    public Object clone()
    {
        return new ANegExpr6(
            cloneNode(this._opMinus_),
            cloneNode(this._e_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANegExpr6(this);
    }

    public TOpMinus getOpMinus()
    {
        return this._opMinus_;
    }

    public void setOpMinus(TOpMinus node)
    {
        if(this._opMinus_ != null)
        {
            this._opMinus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._opMinus_ = node;
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
            + toString(this._opMinus_)
            + toString(this._e_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._opMinus_ == child)
        {
            this._opMinus_ = null;
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
        if(this._opMinus_ == oldChild)
        {
            setOpMinus((TOpMinus) newChild);
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