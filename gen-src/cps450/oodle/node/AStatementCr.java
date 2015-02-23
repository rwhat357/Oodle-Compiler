/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import java.util.*;
import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AStatementCr extends PStatementCr
{
    private PStatement _statement_;
    private final LinkedList<TCr> _cr_ = new LinkedList<TCr>();

    public AStatementCr()
    {
        // Constructor
    }

    public AStatementCr(
        @SuppressWarnings("hiding") PStatement _statement_,
        @SuppressWarnings("hiding") List<?> _cr_)
    {
        // Constructor
        setStatement(_statement_);

        setCr(_cr_);

    }

    @Override
    public Object clone()
    {
        return new AStatementCr(
            cloneNode(this._statement_),
            cloneList(this._cr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStatementCr(this);
    }

    public PStatement getStatement()
    {
        return this._statement_;
    }

    public void setStatement(PStatement node)
    {
        if(this._statement_ != null)
        {
            this._statement_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._statement_ = node;
    }

    public LinkedList<TCr> getCr()
    {
        return this._cr_;
    }

    public void setCr(List<?> list)
    {
        for(TCr e : this._cr_)
        {
            e.parent(null);
        }
        this._cr_.clear();

        for(Object obj_e : list)
        {
            TCr e = (TCr) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._cr_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._statement_)
            + toString(this._cr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._statement_ == child)
        {
            this._statement_ = null;
            return;
        }

        if(this._cr_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._statement_ == oldChild)
        {
            setStatement((PStatement) newChild);
            return;
        }

        for(ListIterator<TCr> i = this._cr_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((TCr) newChild);
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