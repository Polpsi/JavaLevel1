package HomeWork2;

import java.util.Arrays;

public class HomeWork2 {
    public static void main(String[] args){
        //Задание 1, метод invert
        System.out.println("---------Задание 1---------");
        int[] arr1 = new int[]{1, 0, 1, 1, 0, 1, 0, 0, 1};
        System.out.println(Arrays.toString(arr1) + " - исходный массив");
        System.out.println(Arrays.toString(invert(arr1)) + " - инверт. массив");

        //Задание 2, метод fillValue
        System.out.println("---------Задание 2---------");
        int[] arr2 = new int[8];
        System.out.println(Arrays.toString(fillValue(arr2)) + " - заполненный массив");

        //Задание 3, метод multiplyOnTwo
        System.out.println("---------Задание 3---------");
        int[] arr3 ={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arr3) + " - исходный массив");
        System.out.println(Arrays.toString(multiplyOnTwo(arr3)) + " - обработанный массив");

        //Задание 4, методы findMin,findMax
        System.out.println("---------Задание 4---------");
        /*int[] arr4 = new int[(int)(Math.random()*10)];
        for(i=0;i<arr4.length;i++)
            arr4[i]=(int)(Math.random()*100);*/
        int[] arr4={7,12,65,82,45,96,32,8,78,5};
        System.out.println(Arrays.toString(arr4) + " - исходный массив, п.4");
        System.out.println("Минимальное значение - " + findMin(arr4));
        System.out.println("Максимальное значение - " + findMax(arr4));

        //Задание 5, метод diagonal
        System.out.println("---------Задание 5---------");
        int[][] arr5 = new int[4][4];
        arr5=diagonal(arr5);
        for (int i=0;i<arr5.length;i++){
            for(int j=0;j<arr5[i].length;j++)
                System.out.print(arr5[i][j]+" ");
            System.out.println("");
        }

        //Задание 6, метод happyTicket
        System.out.println("---------Задание 6---------");
        int[] arr61 ={2,2,2,2,5,3}; //true 4:2
        int[] arr62 ={1,2,2,2,2,5,3}; //false
        System.out.println((happyTicket(arr61))?"Выпал счастливый билетик":"Нет счастья в этом мире");
        System.out.println((happyTicket(arr62))?"Выпал счастливый билетик":"Нет счастья в этом мире");

        //Задание 7, метод shiftArr
        System.out.println("---------Задание 7---------");
        int[] arr7={1,2,3,4,5,6};
        int n=-1;
        System.out.println(Arrays.toString(arr7)+" - исходный массив");
        System.out.println(Arrays.toString(shiftArr(arr7,n)) + " - обработанный массив, смещение - "+n);

        //Задание 8, метод shiftArrWithoutAddArr
        System.out.println("---------Задание 8---------");
        int[] arr8={1,2,3,4,5,6,7,8,9};
        n=-2;
        System.out.println(Arrays.toString(arr8)+" - исходный массив");
        System.out.println(Arrays.toString(shiftArrWithoutAddArr(arr8,n)) + " - обработанный массив, смещение - "+n);

    }

    //Задание 1
    private static int[] invert(int[] arr) {
        for(int i=0;i<arr.length;i++)
            arr[i]=(arr[i] == 0)?1:0;
        return arr;
    }

    //Задание 2
    private static int[] fillValue(int[] arr) {
        //для заполнения строго 8-ми первых значений массива любой длины, вместо arr.length нужно ставить 8.
        for(int i=0;i<arr.length;i++)
            arr[i]=i*3+1;
        return arr;
    }

    //Задание 3
    private static int[] multiplyOnTwo(int[] arr) {
        for (int i=0;i<arr.length;i++)
            if (arr[i] < 6) arr[i] *= 2;
            // было arr[i]=(arr[i]<6)?arr[i]*2:arr[i];
        return arr;
    }

    //Задание 4
    private static int findMin (int[] arr) {
        int val=arr[0];
        for (int i=1;i<arr.length;i++)
            val=(val > arr[i])?arr[i]:val;
        return val;
    }
    private static int findMax (int[] arr) {
        int val=arr[0];
        for (int i=1;i<arr.length;i++)
            val=(val < arr[i])?arr[i]:val;
        return val;
    }

    //Задание 5
    private static int[][] diagonal(int[][] arr){
        int k = ((arr.length%2 ==0)?arr.length/2:(arr.length+1)/2)-1;
        for (int i = 0; i <= k; i++) {
            arr[i][i]=1;
            //if ((i==k)&&(arr.length%2 !=0)) break; На больших массивах только затормозит.
            arr[arr.length-i-1][i]=1;
            arr[i][arr.length-i-1]=1;
            arr[arr.length-i-1][arr.length-i-1]=1;
        }
/* было
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j]=((i==j)||(i+j==arr[i].length-1))?1:0;

 */
        return arr;
    }

    //Задание 6
    private static boolean happyTicket(int[] arr) {
        int leftSum = arr[0], rightSum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                rightSum += arr[j];
            }
            if(leftSum==rightSum) return true;
            rightSum=0;
            leftSum+=arr[i];
        }
        return false;

        /* было внутри цикла i
            for (int j = 0; j < i; j++)
                leftSum += arr[j];
            for (int j = i; j < arr.length; j++)
                rightSum += arr[j];
         */
    }

    //Задание 7
    private static int[] shiftArr(int[] arr, int n) {
        int[] arrMod = new int[arr.length];
        for (int i = 0; i < arr.length ; i++) {
            arrMod[((i+arr.length+n)%arr.length)]=arr[i];
        }
        return arrMod;
    }

    //Задание 8
    /* Метод смещает позиции элементов массива на единицу (влево или вправо, зависит от положительности n)
    Повторяется |n| раз. */
    private static int[] shiftArrWithoutAddArr(int[] arr,int n) {
        for (int i = 0; i < ((n<0)?-1*n:n); i++) {
            int valTmp=arr[(n<0)?0:arr.length-1];
            if (n<0)
                for (int j=0;j<arr.length-1;j++) //сдвигаются все элементы на одну позицию влево
                    arr[j]=arr[j+1];
            else
                for (int j=arr.length-1;j>0;j--) //сдвигаются все элементы на одну позицию вправо
                    arr[j]=arr[j-1];
            arr[(n<0)?arr.length-1:0]=valTmp;
        }
        return arr;
    }
}
