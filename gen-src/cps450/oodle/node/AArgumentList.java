/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import java.util.*;
import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AArgumentList extends PArgumentList
{
    private TIdentifier _identifier_;
    private TColon _colon_;
    private PTypeProduction _typeProduction_;
    private final LinkedList<PArgumentListTail> _argumentListTail_ = new LinkedList<PArgumentListTail>();

    public AArgumentList()
    {
        // Constructor
    }

    public AArgumentList(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PTypeProduction _typeProduction_,
        @SuppressWarnings("hiding") List<?> _argumentListTail_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setColon(_colon_);

        setTypeProduction(_typeProduction_);

        setArgumentListTail(_argumentListTail_);

    }

    @Override
    public Object clone()
    {
        return new AArgumentList(
            cloneNode(this._identifier_),
            cloneNode(this._colon_),
            cloneNode(this._typeProduction_),
            cloneList(this._argumentListTail_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArgumentList(this);
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public TColon getColon()
    {
        return this._colon_;
    }

    public void setColon(TColon node)
    {
        if(this._colon_ != null)
        {
            this._colon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colon_ = node;
    }

    public PTypeProduction getTypeProduction()
    {
        return this._typeProduction_;
    }

    public void setTypeProduction(PTypeProduction node)
    {
        if(this._typeProduction_ != null)
        {
            this._typeProduction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._typeProduction_ = node;
    }

    public LinkedList<PArgumentListTail> getArgumentListTail()
    {
        return this._argumentListTail_;
    }

    public void setArgumentListTail(List<?> list)
    {
        for(PArgumentListTail e : this._argumentListTail_)
        {
            e.parent(null);
        }
        this._argumentListTail_.clear();

        for(Object obj_e : list)
        {
            PArgumentListTail e = (PArgumentListTail) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._argumentListTail_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._colon_)
            + toString(this._typeProduction_)
            + toString(this._argumentListTail_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._typeProduction_ == child)
        {
            this._typeProduction_ = null;
            return;
        }

        if(this._argumentListTail_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._typeProduction_ == oldChild)
        {
            setTypeProduction((PTypeProduction) newChild);
            return;
        }

        for(ListIterator<PArgumentListTail> i = this._argumentListTail_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PArgumentListTail) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}