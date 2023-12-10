import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Утилиты для сериализации и десериализации объектов.
 */
public class SerializationUtils {
  /**
   * Сохраняет объект в файл с уникальным именем.
   *
   * @param object Сериализуемый объект.
   */
  public static void saveToFile(Serializable object){
    // Генерируем уникальное имя файла на основе имени класса и UUID
    String fileName = object.getClass().getName() + "_" + UUID.randomUUID().toString();

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
      // Сериализуем объект и сохраняем в файл
      oos.writeObject(object);
      System.out.println("Объект успешно сохранен в файл: " + fileName);
    } catch (IOException e){
      e.printStackTrace();
    }
  }

}
