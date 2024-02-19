import java.util.HashMap;
import java.util.Map;

class Solution {
    public int search(String pat, String txt) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : pat.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        int k = pat.length();
        int count = m.size();
        int ans = 0;
        int i = 0, j = 0;

        while (j < txt.length()) {
            char currentChar = txt.charAt(j);
            if (m.containsKey(currentChar)) {
                m.put(currentChar, m.get(currentChar) - 1);
                if (m.get(currentChar) == 0)
                    count--;
            }

            if (j - i + 1 < k)
                j++;
            else if (j - i + 1 == k) {
                if (count == 0)
                    ans++;

                char removedChar = txt.charAt(i);
                if (m.containsKey(removedChar)) {
                    m.put(removedChar, m.get(removedChar) + 1);
                    if (m.get(removedChar) == 1)
                        count++;
                }

                i++;
                j++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String pat = "abc";
        String txt = "abcabcabc";
        int count = solution.search(pat, txt);
        System.out.println("Number of occurrences: " + count); // Output: 3
    }
}
