/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AVarDecl extends PVarDecl
{
    private TId _id_;
    private PPartType _partType_;
    private PPartExp _partExp_;
    private PNewlines _newlines_;

    public AVarDecl()
    {
        // Constructor
    }

    public AVarDecl(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") PPartType _partType_,
        @SuppressWarnings("hiding") PPartExp _partExp_,
        @SuppressWarnings("hiding") PNewlines _newlines_)
    {
        // Constructor
        setId(_id_);

        setPartType(_partType_);

        setPartExp(_partExp_);

        setNewlines(_newlines_);

    }

    @Override
    public Object clone()
    {
        return new AVarDecl(
            cloneNode(this._id_),
            cloneNode(this._partType_),
            cloneNode(this._partExp_),
            cloneNode(this._newlines_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarDecl(this);
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

    public PPartType getPartType()
    {
        return this._partType_;
    }

    public void setPartType(PPartType node)
    {
        if(this._partType_ != null)
        {
            this._partType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._partType_ = node;
    }

    public PPartExp getPartExp()
    {
        return this._partExp_;
    }

    public void setPartExp(PPartExp node)
    {
        if(this._partExp_ != null)
        {
            this._partExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._partExp_ = node;
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
            + toString(this._id_)
            + toString(this._partType_)
            + toString(this._partExp_)
            + toString(this._newlines_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._partType_ == child)
        {
            this._partType_ = null;
            return;
        }

        if(this._partExp_ == child)
        {
            this._partExp_ = null;
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
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._partType_ == oldChild)
        {
            setPartType((PPartType) newChild);
            return;
        }

        if(this._partExp_ == oldChild)
        {
            setPartExp((PPartExp) newChild);
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
