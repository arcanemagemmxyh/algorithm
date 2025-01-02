public class StringHash {
    private static final int BASE = 31;
    private static final int MOD = 1_000_000_007;
    private long[] prefixHash;
    private long[] powerBase;

    public StringHash(String s) {
        int n = s.length();
        prefixHash = new long[n + 1];
        powerBase = new long[n + 1];

        powerBase[0] = 1;
        for (int i = 0; i < n; i++) {
            prefixHash[i + 1] = (prefixHash[i] * BASE + (s.charAt(i) - '0' + 1)) % MOD;
            powerBase[i + 1] = (powerBase[i] * BASE) % MOD;
        }
    }

    public long getSubstringHash(int l, int r) {
        return (prefixHash[r + 1] - (prefixHash[l] * powerBase[r - l + 1]) + MOD) % MOD;
    }

    public boolean isPrefix(int l1, int r1, int l2, int r2) {
        return getSubstringHash(l1, r1) == getSubstringHash(l2, r2);
    }
}