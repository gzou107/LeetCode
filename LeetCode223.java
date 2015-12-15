/*
Rectangle Area My Submissions Question
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
×/
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = getArea(A, B, C, D);
        int area2 = getArea(E, F, G, H);
        long overLapX = getOverlap(A,C,E,G);
        long overLapY = getOverlap(B,D,F,H);
        
        if(overLapX <=0 || overLapY <= 0){
            return area1 + area2;
        }
        return area1+area2 - (int)(overLapX*overLapY);
    }
    
    private static boolean isOverlap(int m, int n, int p, int q){
        return !(n <=p || q <=m);
    }
    
    // the return type must be long as it can be bigger than int can hold
    private static long getOverlap(int m, int n, int p, int q){
        return (long)Math.min(n, q) - Math.max(m, p);
    }
    
    private static int getArea(int m, int n, int p, int q){
        return (p-m)*(q-n);
    }
}