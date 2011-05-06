package uk.me.graphe.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Edge {
    private Vertex mVertex1;
    private Vertex mVertex2;
    private VertexDirection mDir;
    private List<Integer> mWeights = new ArrayList<Integer>();

    /**
     * creates an edge without direction
     * 
     * @param v1
     *            the "from" vertex
     * @param v2
     *            the "to" vertex
     */
    public Edge(Vertex v1, Vertex v2) {
        this(v1, v2, VertexDirection.both);
    }

    /**
     * creates an edge
     * 
     * @param v1
     *            the "from" vertex
     * @param v2
     *            the "to" vertex
     * @param dir
     *            the edge direction
     */
    public Edge(Vertex v1, Vertex v2, VertexDirection dir) {
        mVertex1 = v1;
        mVertex2 = v2;
        mDir = dir;
        mWeights.add(0);
    }

    /**
     * determines if the edge exits a vertex
     * 
     * @param v
     *            the vertex to test against
     * @return true if this edge exits v, else false
     */
    public boolean exits(Vertex v) {
        if (mDir == VertexDirection.both
                && (v.equals(mVertex1) || v.equals(mVertex2))) return true;
        else
            return v.equals((mDir == VertexDirection.fromTo) ? mVertex1
                    : mVertex2);
    }

    /**
     * determines if the edge enters a vertex
     * 
     * @param v
     *            the vertex to test against
     * @return true if this edge enters v, else false
     */
    public boolean enters(Vertex v) {
        if (mDir == VertexDirection.both
                && (v.equals(mVertex1) || v.equals(mVertex2))) return true;
        else
            return v.equals((mDir == VertexDirection.toFrom) ? mVertex1
                    : mVertex2);
    }

    /**
     * gets the from vertex of this edge
     * 
     * @return
     */
    public Vertex getFromVertex() {
        return mVertex1;
    }

    /**
     * gets the to vertex of this edge
     * 
     * @return
     */
    public Vertex getToVertex() {
        return mVertex2;
    }

    /**
     * determines if an edge has a direction
     * 
     * @return
     */
    public boolean hasDirection() {
        return mDir != VertexDirection.both;
    }

    public VertexDirection getDirection() {
        return mDir;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge cmp = (Edge) o;
            return cmp.mVertex1.equals(mVertex1)
                    && cmp.mVertex2.equals(mVertex2) && cmp.mDir == mDir;
        } else
            return false;
    }

    public void setWeight(int weight, int edgeIndex) {
        if (weight >= 0) mWeights.set(edgeIndex, weight);
    }

    public int getWeight(int edgeIndex) {
        return mWeights.get(edgeIndex);
    }
    
    @Override
    public int hashCode() {
        return mVertex1.hashCode() + mVertex2.hashCode();
    }
    
    public void addMultipleEdge(int weight) {
        mWeights.add(weight);
    }
    
    public List<Integer> getAllWeights() {
        return Collections.unmodifiableList(this.mWeights);
    }

}
