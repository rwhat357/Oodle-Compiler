/* This file was generated by SableCC (http://www.sablecc.org/). */

package cps450.oodle.node;

import java.util.*;
import cps450.oodle.analysis.*;

@SuppressWarnings("nls")
public final class AMethodDecl extends PMethodDecl
{
    private TId _stid_;
    private TLPar _lPar_;
    private PArgumentDeclList _argumentDeclList_;
    private TRPar _rPar_;
    private PPartType _partType_;
    private TIs _is_;
    private PNewlines _n1_;
    private final LinkedList<PVarDecl> _varDecl_ = new LinkedList<PVarDecl>();
    private TBegin _begin_;
    private PNewlines _n2_;
    private final LinkedList<PStmt> _stmt_ = new LinkedList<PStmt>();
    private TEnd _end_;
    private TId _endid_;
    private PNewlines _n3_;

    public AMethodDecl()
    {
        // Constructor
    }

    public AMethodDecl(
        @SuppressWarnings("hiding") TId _stid_,
        @SuppressWarnings("hiding") TLPar _lPar_,
        @SuppressWarnings("hiding") PArgumentDeclList _argumentDeclList_,
        @SuppressWarnings("hiding") TRPar _rPar_,
        @SuppressWarnings("hiding") PPartType _partType_,
        @SuppressWarnings("hiding") TIs _is_,
        @SuppressWarnings("hiding") PNewlines _n1_,
        @SuppressWarnings("hiding") List<?> _varDecl_,
        @SuppressWarnings("hiding") TBegin _begin_,
        @SuppressWarnings("hiding") PNewlines _n2_,
        @SuppressWarnings("hiding") List<?> _stmt_,
        @SuppressWarnings("hiding") TEnd _end_,
        @SuppressWarnings("hiding") TId _endid_,
        @SuppressWarnings("hiding") PNewlines _n3_)
    {
        // Constructor
        setStid(_stid_);

        setLPar(_lPar_);

        setArgumentDeclList(_argumentDeclList_);

        setRPar(_rPar_);

        setPartType(_partType_);

        setIs(_is_);

        setN1(_n1_);

        setVarDecl(_varDecl_);

        setBegin(_begin_);

        setN2(_n2_);

        setStmt(_stmt_);

        setEnd(_end_);

        setEndid(_endid_);

        setN3(_n3_);

    }

    @Override
    public Object clone()
    {
        return new AMethodDecl(
            cloneNode(this._stid_),
            cloneNode(this._lPar_),
            cloneNode(this._argumentDeclList_),
            cloneNode(this._rPar_),
            cloneNode(this._partType_),
            cloneNode(this._is_),
            cloneNode(this._n1_),
            cloneList(this._varDecl_),
            cloneNode(this._begin_),
            cloneNode(this._n2_),
            cloneList(this._stmt_),
            cloneNode(this._end_),
            cloneNode(this._endid_),
            cloneNode(this._n3_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMethodDecl(this);
    }

    public TId getStid()
    {
        return this._stid_;
    }

    public void setStid(TId node)
    {
        if(this._stid_ != null)
        {
            this._stid_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._stid_ = node;
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

    public PArgumentDeclList getArgumentDeclList()
    {
        return this._argumentDeclList_;
    }

    public void setArgumentDeclList(PArgumentDeclList node)
    {
        if(this._argumentDeclList_ != null)
        {
            this._argumentDeclList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._argumentDeclList_ = node;
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

    public TIs getIs()
    {
        return this._is_;
    }

    public void setIs(TIs node)
    {
        if(this._is_ != null)
        {
            this._is_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._is_ = node;
    }

    public PNewlines getN1()
    {
        return this._n1_;
    }

    public void setN1(PNewlines node)
    {
        if(this._n1_ != null)
        {
            this._n1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._n1_ = node;
    }

    public LinkedList<PVarDecl> getVarDecl()
    {
        return this._varDecl_;
    }

    public void setVarDecl(List<?> list)
    {
        for(PVarDecl e : this._varDecl_)
        {
            e.parent(null);
        }
        this._varDecl_.clear();

        for(Object obj_e : list)
        {
            PVarDecl e = (PVarDecl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._varDecl_.add(e);
        }
    }

    public TBegin getBegin()
    {
        return this._begin_;
    }

    public void setBegin(TBegin node)
    {
        if(this._begin_ != null)
        {
            this._begin_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._begin_ = node;
    }

    public PNewlines getN2()
    {
        return this._n2_;
    }

    public void setN2(PNewlines node)
    {
        if(this._n2_ != null)
        {
            this._n2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._n2_ = node;
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

    public TEnd getEnd()
    {
        return this._end_;
    }

    public void setEnd(TEnd node)
    {
        if(this._end_ != null)
        {
            this._end_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._end_ = node;
    }

    public TId getEndid()
    {
        return this._endid_;
    }

    public void setEndid(TId node)
    {
        if(this._endid_ != null)
        {
            this._endid_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._endid_ = node;
    }

    public PNewlines getN3()
    {
        return this._n3_;
    }

    public void setN3(PNewlines node)
    {
        if(this._n3_ != null)
        {
            this._n3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._n3_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._stid_)
            + toString(this._lPar_)
            + toString(this._argumentDeclList_)
            + toString(this._rPar_)
            + toString(this._partType_)
            + toString(this._is_)
            + toString(this._n1_)
            + toString(this._varDecl_)
            + toString(this._begin_)
            + toString(this._n2_)
            + toString(this._stmt_)
            + toString(this._end_)
            + toString(this._endid_)
            + toString(this._n3_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._stid_ == child)
        {
            this._stid_ = null;
            return;
        }

        if(this._lPar_ == child)
        {
            this._lPar_ = null;
            return;
        }

        if(this._argumentDeclList_ == child)
        {
            this._argumentDeclList_ = null;
            return;
        }

        if(this._rPar_ == child)
        {
            this._rPar_ = null;
            return;
        }

        if(this._partType_ == child)
        {
            this._partType_ = null;
            return;
        }

        if(this._is_ == child)
        {
            this._is_ = null;
            return;
        }

        if(this._n1_ == child)
        {
            this._n1_ = null;
            return;
        }

        if(this._varDecl_.remove(child))
        {
            return;
        }

        if(this._begin_ == child)
        {
            this._begin_ = null;
            return;
        }

        if(this._n2_ == child)
        {
            this._n2_ = null;
            return;
        }

        if(this._stmt_.remove(child))
        {
            return;
        }

        if(this._end_ == child)
        {
            this._end_ = null;
            return;
        }

        if(this._endid_ == child)
        {
            this._endid_ = null;
            return;
        }

        if(this._n3_ == child)
        {
            this._n3_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._stid_ == oldChild)
        {
            setStid((TId) newChild);
            return;
        }

        if(this._lPar_ == oldChild)
        {
            setLPar((TLPar) newChild);
            return;
        }

        if(this._argumentDeclList_ == oldChild)
        {
            setArgumentDeclList((PArgumentDeclList) newChild);
            return;
        }

        if(this._rPar_ == oldChild)
        {
            setRPar((TRPar) newChild);
            return;
        }

        if(this._partType_ == oldChild)
        {
            setPartType((PPartType) newChild);
            return;
        }

        if(this._is_ == oldChild)
        {
            setIs((TIs) newChild);
            return;
        }

        if(this._n1_ == oldChild)
        {
            setN1((PNewlines) newChild);
            return;
        }

        for(ListIterator<PVarDecl> i = this._varDecl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PVarDecl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._begin_ == oldChild)
        {
            setBegin((TBegin) newChild);
            return;
        }

        if(this._n2_ == oldChild)
        {
            setN2((PNewlines) newChild);
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

        if(this._end_ == oldChild)
        {
            setEnd((TEnd) newChild);
            return;
        }

        if(this._endid_ == oldChild)
        {
            setEndid((TId) newChild);
            return;
        }

        if(this._n3_ == oldChild)
        {
            setN3((PNewlines) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}