public class Z_algorithm {
    int[] z;
    String str;
    public Z_algorithm(String str) {
        this.str = str;
        z = new int[str.length()];
        build(str);
    }

    public void build(String str) {
        int l = 0,r = 0;
        for(int i=1;i<str.length();i++) {
            if(i<=r){
                z[i] = Math.min(z[i-l],r-i+1);
            }
            while(i+z[i]<str.length()&&str.charAt(i+z[i])==str.charAt(i)){
                l=i;
                r=i+z[i];
                z[i]++;
            }
        }
    }

}