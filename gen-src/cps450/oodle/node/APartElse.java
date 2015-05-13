/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import java.util.*;
import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class APartElse extends PPartElse
{
    private TElse _else_;
    private PNewlines _newlines_;
    private final LinkedList<PStmt> _stmt_ = new LinkedList<PStmt>();

    public APartElse()
    {
        // Constructor
    }

    public APartElse(
        @SuppressWarnings("hiding") TElse _else_,
        @SuppressWarnings("hiding") PNewlines _newlines_,
        @SuppressWarnings("hiding") List<?> _stmt_)
    {
        // Constructor
        setElse(_else_);

        setNewlines(_newlines_);

        setStmt(_stmt_);

    }

    @Override
    public Object clone()
    {
        return new APartElse(
            cloneNode(this._else_),
            cloneNode(this._newlines_),
            cloneList(this._stmt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPartElse(this);
    }

    public TElse getElse()
    {
        return this._else_;
    }

    public void setElse(TElse node)
    {
        if(this._else_ != null)
        {
            this._else_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._else_ = node;
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

    public LinkedList<PStmt> getStmt()
    {
        return this._stmt_;
    }

    public void setStmt(List<?> list)
    {
        for(PStmt e : this._stmt_)
        {
            e.parent(null);
        }
        this._stmt_.clear();

        for(Object obj_e : list)
        {
            PStmt e = (PStmt) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._stmt_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._else_)
            + toString(this._newlines_)
            + toString(this._stmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._else_ == child)
        {
            this._else_ = null;
            return;
        }

        if(this._newlines_ == child)
        {
            this._newlines_ = null;
            return;
        }

        if(this._stmt_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._else_ == oldChild)
        {
            setElse((TElse) newChild);
            return;
        }

        if(this._newlines_ == oldChild)
        {
            setNewlines((PNewlines) newChild);
            return;
        }

        for(ListIterator<PStmt> i = this._stmt_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStmt) newChild);
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