package task2009;

import java.io.*;

/* 
Как сериализовать static?
Сделай так, чтобы сериализация класса ClassWithStatic была возможной.


Requirements:
1. Класс ClassWithStatic должен существовать внутри класса Solution.
2. Класс ClassWithStatic должен быть статическим.
3. Класс ClassWithStatic должен быть публичным.
4. Класс ClassWithStatic должен поддерживать интерфейс Serializable.*/

public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "This is a static test string";
        public int i;
        public int j;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "2009.txt";
        ClassWithStatic staticClass1 = new ClassWithStatic();
        staticClass1.i = 50;
        staticClass1.j = 30;

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName));

        writer.writeObject(staticClass1);

        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
        ClassWithStatic staticClass2 = (ClassWithStatic) reader.readObject();

        System.out.printf("i="+ staticClass2.i+" "+ staticClass2.j+" "+ staticClass2.staticString);
    }
}
