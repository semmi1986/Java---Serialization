package task2011;

import java.io.*;

/* 
Externalizable для апартаментов
Реализуй интерфейс Externalizable в классе Apartment.


Requirements:
1. Класс Solution.Apartment должен поддерживать интерфейс Externalizable.
2. В классе Solution.Apartment должен быть реализован метод writeExternal с одним параметром типа ObjectOutput.
3. В классе Solution.Apartment должен быть реализован метод readExternal с одним параметром типа ObjectInput.
4. В методе writeExternal, на полученном в качестве параметра объекте типа ObjectOutput должен быть вызван метод writeObject с параметром address.
5. В методе writeExternal, на полученном в качестве параметра объекте типа ObjectOutput должен быть вызван метод writeInt с параметром year.
6. Метод readExternal должен корректно восстанавливать из ObjectInput значение поля address.
7. Метод readExternal должен корректно восстанавливать из ObjectInput значение поля year.*/

public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() {
            super();
        }

        public Apartment(String addr, int y) {
            address = addr;
            year = y;
        }

        /**
         * Prints out the fields used for testing!
         */
        public String toString() {
            return ("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(this.address);
            out.writeInt(this.year);
            out.close();
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.address = (String)in.readObject();
            this.year = in.readInt();
            in.close();
        }
    }

    public static void main(String[] args) {
        Apartment apartment = new Apartment("123 Main St", 2020);

        // Имя файла для сериализации
        String filename = "2011.txt";

        // Сериализация объекта в файл
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            apartment.writeExternal(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация объекта из файла
        Apartment deserializedApartment = new Apartment();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedApartment.readExternal(in);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Вывод десериализованного объекта в консоль
        System.out.println("Deserialized Apartment:");
        System.out.println(deserializedApartment);
    }
}
