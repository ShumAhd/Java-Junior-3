import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Утилиты для сериализации и десериализации объектов.
 */
public class SerializationUtils {

  /**
   * Сохраняет объект в файл с уникальным именем
   *
   * @param object Сериализуемый объект.
   */
  public static void saveToFile(Serializable object) {
    // Генерируем уникальное имя файла на основе имени класса и UUID
    String fileName = object.getClass().getName() + "_" + UUID.randomUUID().toString();

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      // Сериализуем объект и сохраняем в файл
      oos.writeObject(object);
      System.out.println("Объект успешно сохранен в файл: " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Загружает объект из файла по указанному имени и удаляет файл.
   *
   * @param fileName Имя файла.
   * @return Загруженный объект или null в случае ошибки.
   */
  public static Object loadFromFile(String fileName) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
      // Читаем объект из файла и возвращаем его
      Object loadedObject = ois.readObject();
      System.out.println("Объект успешно загружен из файла: " + fileName);
      deleteFile(fileName); // Удаляем файл после успешной загрузки
      return loadedObject;
    } catch (FileNotFoundException e) {
      System.err.println("Файл не найден: " + fileName);
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Ошибка при чтении файла или десериализации объекта: " + e.getMessage());
    }
    return null;
  }
}