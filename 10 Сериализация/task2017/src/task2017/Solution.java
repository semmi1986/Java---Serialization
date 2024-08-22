package task2017;

import java.io.*;

/* 
Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуй объект в методе getOriginalObject так, чтобы в случае возникновения исключения было выведено сообщение на экран и возвращен null.
Реализуй интерфейс Serializable где необходимо.


Requirements:
1. Класс B должен быть потомком класса A.
2. Класс A должен поддерживать интерфейс Serializable.
3. Класс B не должен явно поддерживать интерфейс Serializable.
4. Метод getOriginalObject должен возвращать объект типа A полученный из потока ObjectInputStream.
5. Метод getOriginalObject должен возвращать null, если при попытке десериализации не был получен объект типа A.
6. Метод getOriginalObject должен возвращать null, если при попытке десериализации возникло исключение.*/

public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        try {
            // Пытаемся десериализовать объект из потока
            Object obj = objectStream.readObject();

            // Проверяем, является ли объект экземпляром класса A
            if (obj instanceof A) {
                return (A) obj;
            }
        } catch (Exception e) {
            // В случае любого исключения выводим сообщение и возвращаем null
            System.out.println("An exception occurred: " + e);
        }

        // Если объект не был экземпляром A или произошло исключение
        return null;
    }

    public class A implements Serializable {
        // Класс A реализует Serializable, поэтому можно его десериализовать
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
