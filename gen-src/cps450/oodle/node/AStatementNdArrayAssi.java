/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AStatementNdArrayAssi extends PStatementNdArrayAssi
{
    private TLeftBracket _leftBracket_;
    private PExpression _expression_;
    private TRightBracket _rightBracket_;

    public AStatementNdArrayAssi()
    {
        // Constructor
    }

    public AStatementNdArrayAssi(
        @SuppressWarnings("hiding") TLeftBracket _leftBracket_,
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") TRightBracket _rightBracket_)
    {
        // Constructor
        setLeftBracket(_leftBracket_);

        setExpression(_expression_);

        setRightBracket(_rightBracket_);

    }

    @Override
    public Object clone()
    {
        return new AStatementNdArrayAssi(
            cloneNode(this._leftBracket_),
            cloneNode(this._expression_),
            cloneNode(this._rightBracket_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStatementNdArrayAssi(this);
    }

    public TLeftBracket getLeftBracket()
    {
        return this._leftBracket_;
    }

    public void setLeftBracket(TLeftBracket node)
    {
        if(this._leftBracket_ != null)
        {
            this._leftBracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._leftBracket_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    public TRightBracket getRightBracket()
    {
        return this._rightBracket_;
    }

    public void setRightBracket(TRightBracket node)
    {
        if(this._rightBracket_ != null)
        {
            this._rightBracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rightBracket_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._leftBracket_)
            + toString(this._expression_)
            + toString(this._rightBracket_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._leftBracket_ == child)
        {
            this._leftBracket_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._rightBracket_ == child)
        {
            this._rightBracket_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._leftBracket_ == oldChild)
        {
            setLeftBracket((TLeftBracket) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(this._rightBracket_ == oldChild)
        {
            setRightBracket((TRightBracket) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
