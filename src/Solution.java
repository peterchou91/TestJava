/**
 * Created by zhouqing on 15/9/9.
 */

import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;

import java.io.*;
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if(total%2 == 1){
            System.out.println("奇数");
            return find_kth(nums1,0,nums1.length,nums2,0,nums2.length,total / 2 + 1);
        }else{
            int a = find_kth(nums1,0,nums1.length,nums2,0,nums2.length,total / 2 + 1);
            int b = find_kth(nums1,0,nums1.length,nums2,0,nums2.length,total / 2);
            System.out.println("a:" + a + " b:" + b);
            return (a + b)/2.0;
        }

    }

    int find_kth(int[] nums1,int start1,int n1,int[] nums2,int start2,int n2,int k){
        if(n1 > n2){
            return find_kth(nums2,start2,n2,nums1,start1,n1,k);
        }
        if(n1 == 0) return nums2[k - 1 + start2];
        if(k == 1) return Math.min(nums1[start1],nums2[start2]);

        int a = Math.min(k/2,n1);
        int b = k - a;
        if(nums1[start1 + a - 1] < nums2[start2 + b - 1]){
            return find_kth(nums1,start1 + a,n1 - a,nums2,start2,n2,k - a);
        }else if (nums1[start1 + a - 1] > nums2[start2 + b - 1]){
            return find_kth(nums1,start1,n1,nums2,start2 + b,n2 - b,k - b);
        }else{
            return nums1[start1 + a - 1];
        }
    }
    public int reverse(int x) {
        int ret = 0;
        while(x!=0){
            int c = x%10;
            x = x/10;
            System.out.println(ret);
            ret = ret*10 + c;
            System.out.println(c);
            System.out.println(x);
            System.out.println(ret);

        }
        return ret;
    }
    int addDigits(int num) {

        if(num / 10 == 0) return num;
        while(num/10 != 0){
            int sum = 0;
            while(num != 0){
                int c = num % 10;
                num = num / 10;
                sum += c;
            }
            num = sum;
        }
        return num;
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if(k <= 0) return null;
        List<int[]> retList = null;
        int i = 0, j = 0;
        
        return retList;

    }
    int[] next(String needle){
        int[] next = new int[needle.length()];
        next[0] = -1;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i = 1 ; i < needle.length(); i++){
            stack.push(next[i - 1]);
            while(!stack.isEmpty()){
                int j = stack.pop();
                if(j == -1) {
                    next[i] = 0;
                    break;
                }
                if(needle.charAt(i - 1) == needle.charAt(j)){
                    next[i] = j + 1;
                    break;
                }
                stack.push(next[j]);

            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        if(arr.length == 0) return s;
        StringBuilder sb = new StringBuilder();
        char sep = '#';
        for(char c : arr){
            sb.append(sep).append(c);
        }
        sb.append(sep);
        System.out.println(sb.toString());
        arr = sb.toString().toCharArray();

        int[] p = new int[arr.length];
        int id = 0;
        int mx = 0;
        p[0] = 1;
        for(int i = 1; i < arr.length; ++i){
            p[i] = 0;
            if(i < mx){
                int j = 2*id - i;//2*id - i: i关于id的对称点
                p[i] = Math.min(mx - i + 1,p[j]);
            }
            while(i - p[i] >= 0 && i + p[i] < arr.length
                    && arr[i - p[i]] == arr[i + p[i]])
            {
                p[i]++;
            }
            if(p[i] + i - 1 > mx){
                id = i;
                mx = p[id] + id - 1;
            }
        }

        int maxLen = 0;
        int maxId = 0;
        for(int k = 0; k < p.length; ++k){
            if(maxLen < p[k]){
                maxLen = p[k];
                maxId = k;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int j = maxId - maxLen + 1; j < maxId + maxLen; ++j){
            if('#'!=arr[j]) result.append(arr[j]);
        }
        System.out.println(Arrays.toString(p));
        return result.toString();
    }

    public static void main(String[] args) throws Exception{
        Solution s = new Solution();

        //System.out.println(s.addDigits(19));
        //System.out.println(964632435 * 10);
//        System.out.println((int)Character.MAX_VALUE);
        //System.out.println(s.findMedianSortedArrays(new int[]{1,2},new int[]{1,2}));
        //s.next("abaabcac");
        //s.longestPalindrome("abaaba");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();

       // ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./object"));
       // out.writeObject(new People("qing", 20));
       // out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./object"));
        People p = (People)in.readObject();
        System.out.println(p.name);



    }
}

class People implements Serializable{
    //public static final long serialVersionUID = 1L;
    public People(String name,int age){
        this.name = name;
        this.age = age;
    }
    String name = "qing";
    int age = 20;
}