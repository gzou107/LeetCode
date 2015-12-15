/*
Excel Sheet Column Title My Submissions Question
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/
public class Solution {
    public String convertToTitle(int n) {
        
        StringBuilder ans = new StringBuilder();
        while( n > 0){
            char c = (char)('A' + (n - 1)%26);
            ans.append(c);
            n = (n-1)/26;
        }
        return ans.reverse().toString();
    }
}

/*
The result of adding Java chars, shorts, or bytes is an int:

Java Language Specification on Binary Numeric Promotion:

If any of the operands is of a reference type, unboxing conversion (§5.1.8) is performed. Then:
If either operand is of type double, the other is converted to double.
Otherwise, if either operand is of type float, the other is converted to float.
Otherwise, if either operand is of type long, the other is converted to long.
Otherwise, both operands are converted to type int.
But note what it says about compound assignment operators (like +=):

The result of the binary operation is converted to the type of the left-hand variable ... and the result of the conversion is stored into the variable.
For example:

char x = 1, y = 2;
x = x + y; // compile error: "possible loss of precision (found int, required char)"
x = (char)(x + y); // explicit cast back to char; OK
x += y; // compound operation-assignment; also OK
One way you can find out the type of the result, in general, is to cast it to an Object and ask it what class it is:

System.out.println(((Object)('a' + 'b')).getClass());
// outputs: class java.lang.Integer
*/