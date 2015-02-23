/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class ACrClassDefinition extends PCrClassDefinition
{
    private TCr _cr_;
    private PClassDefinition _classDefinition_;

    public ACrClassDefinition()
    {
        // Constructor
    }

    public ACrClassDefinition(
        @SuppressWarnings("hiding") TCr _cr_,
        @SuppressWarnings("hiding") PClassDefinition _classDefinition_)
    {
        // Constructor
        setCr(_cr_);

        setClassDefinition(_classDefinition_);

    }

    @Override
    public Object clone()
    {
        return new ACrClassDefinition(
            cloneNode(this._cr_),
            cloneNode(this._classDefinition_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACrClassDefinition(this);
    }

    public TCr getCr()
    {
        return this._cr_;
    }

    public void setCr(TCr node)
    {
        if(this._cr_ != null)
        {
            this._cr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cr_ = node;
    }

    public PClassDefinition getClassDefinition()
    {
        return this._classDefinition_;
    }

    public void setClassDefinition(PClassDefinition node)
    {
        if(this._classDefinition_ != null)
        {
            this._classDefinition_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classDefinition_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._cr_)
            + toString(this._classDefinition_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._cr_ == child)
        {
            this._cr_ = null;
            return;
        }

        if(this._classDefinition_ == child)
        {
            this._classDefinition_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._cr_ == oldChild)
        {
            setCr((TCr) newChild);
            return;
        }

        if(this._classDefinition_ == oldChild)
        {
            setClassDefinition((PClassDefinition) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
