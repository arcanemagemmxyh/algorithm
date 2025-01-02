class SegmentTree{  //动态开点求区间和
    class Node{
        long sum;
        long lazy;
        Node left,right;

        Node(){
            this.sum = 0;
            this.lazy = 0;
        }
    }

    private final static int INF = (int)(1e9+1);
    private Node root;

    public SegmentTree() {
        root = new Node();
    }

    public long query(int L, int R) {
        return query(root,L,R,1,INF);
    }

    public void update(int L,int R,int val){
        update(root,L,R,1,INF,val);
    }

    private void update(Node node,int L,int R,int l,int r,int val){
        pushDown(node,l,r);
        if(L<=l&&r<=R){
            node.lazy+=val;
            pushDown(node,l,r);
            return;
        }
        int mid = l+r>>>1;
        if(node.left==null) node.left=new Node();
        if(node.right==null) node.right=new Node();
        if(L<=mid){
            update(node.left,L,R,l,mid,val);
        }
        if(R>mid){
            update(node.right,L,R,mid+1,r,val);
        }
        node.sum = getSum(node.left)+getSum(node.right);
    }

    public long getSum(Node node){
        return node==null?0:node.sum;
    }

    private long query(Node node,int L,int R,int l,int r) {
        if(node==null) return 0;
        pushDown(node,l,r);
        if(L<=l && r<=R) {
            return node.sum;
        }
        int mid = l+r>>>1;
        long sum = 0;
        if(L<=mid){
            sum += query(node.left,L,R,l,mid);
        }
        if(mid<R){
            sum += query(node.right,L,R,mid+1,r);
        }
        return sum;
    }

    private void pushDown(Node node,int l,int r) {
        if(node.lazy!=0){
            node.sum+=node.lazy*(r-l+1);
            if(l!=r){
                if(node.left==null){
                    node.left = new Node();
                }
                if(node.right==null){
                    node.right = new Node();
                }
                node.left.lazy+=node.lazy;
                node.right.lazy+=node.lazy;
            }
            node.lazy=0;
        }
    }

}