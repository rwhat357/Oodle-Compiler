/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class ACallStmt extends PStmt
{
    private PPartObj _partObj_;
    private TId _id_;
    private TLPar _lPar_;
    private PExprList _exprList_;
    private TRPar _rPar_;
    private PNewlines _newlines_;

    public ACallStmt()
    {
        // Constructor
    }

    public ACallStmt(
        @SuppressWarnings("hiding") PPartObj _partObj_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TLPar _lPar_,
        @SuppressWarnings("hiding") PExprList _exprList_,
        @SuppressWarnings("hiding") TRPar _rPar_,
        @SuppressWarnings("hiding") PNewlines _newlines_)
    {
        // Constructor
        setPartObj(_partObj_);

        setId(_id_);

        setLPar(_lPar_);

        setExprList(_exprList_);

        setRPar(_rPar_);

        setNewlines(_newlines_);

    }

    @Override
    public Object clone()
    {
        return new ACallStmt(
            cloneNode(this._partObj_),
            cloneNode(this._id_),
            cloneNode(this._lPar_),
            cloneNode(this._exprList_),
            cloneNode(this._rPar_),
            cloneNode(this._newlines_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallStmt(this);
    }

    public PPartObj getPartObj()
    {
        return this._partObj_;
    }

    public void setPartObj(PPartObj node)
    {
        if(this._partObj_ != null)
        {
            this._partObj_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._partObj_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public TLPar getLPar()
    {
        return this._lPar_;
    }

    public void setLPar(TLPar node)
    {
        if(this._lPar_ != null)
        {
            this._lPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lPar_ = node;
    }

    public PExprList getExprList()
    {
        return this._exprList_;
    }

    public void setExprList(PExprList node)
    {
        if(this._exprList_ != null)
        {
            this._exprList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exprList_ = node;
    }

    public TRPar getRPar()
    {
        return this._rPar_;
    }

    public void setRPar(TRPar node)
    {
        if(this._rPar_ != null)
        {
            this._rPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rPar_ = node;
    }

    public PNewlines getNewlines()
    {
        return this._newlines_;
    }

    public void setNewlines(PNewlines node)
    {
        if(this._newlines_ != null)
        {
            this._newlines_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._newlines_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._partObj_)
            + toString(this._id_)
            + toString(this._lPar_)
            + toString(this._exprList_)
            + toString(this._rPar_)
            + toString(this._newlines_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._partObj_ == child)
        {
            this._partObj_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._lPar_ == child)
        {
            this._lPar_ = null;
            return;
        }

        if(this._exprList_ == child)
        {
            this._exprList_ = null;
            return;
        }

        if(this._rPar_ == child)
        {
            this._rPar_ = null;
            return;
        }

        if(this._newlines_ == child)
        {
            this._newlines_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._partObj_ == oldChild)
        {
            setPartObj((PPartObj) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._lPar_ == oldChild)
        {
            setLPar((TLPar) newChild);
            return;
        }

        if(this._exprList_ == oldChild)
        {
            setExprList((PExprList) newChild);
            return;
        }

        if(this._rPar_ == oldChild)
        {
            setRPar((TRPar) newChild);
            return;
        }

        if(this._newlines_ == oldChild)
        {
            setNewlines((PNewlines) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
