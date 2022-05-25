import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class FileReader {

    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файлы с отчётами. Возможные причины:");
            System.out.println("1. Файлы размещены в другой директории");
            System.out.println("2. Некорректное наименование файла");
            System.out.println("Проверьте наименование и расположение файлов с отчётами и перезапустите программу.");
            return null;
        }
    }
}
