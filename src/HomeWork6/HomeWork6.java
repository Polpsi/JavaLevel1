package HomeWork6;

import java.io.*;
import java.util.Date;

public class HomeWork6 {

    public static void main(String[] args) {
        //Первое задание. Файлов были создано около десятка штук. Т.к. содержимое файлов роли не играет - не включал их в git.

        //Второе задание. Склеивать можно в любое место: в первый файл, во второй, вообще в другой файл.
        try {
            gluingFiles(new File("src/files/temp.txt"), new File("src/files/temp2.txt"), new File("src/files/temp2.txt"));
            // Ещё стало интересно не только два файла склеить, но и все файлы в папке. Можно в ту же папку, можно в другую. Я клею в ту же.
            // Если целевой файл один из папки, его содержимое тоже вклеивается по порядку.
            gluingFiles(new File("src/files"), new File("src/files/zglueFolder2.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            //Третье. Ищет первое вхождение в файле, если есть, сообщает - где нашёл.
            Result result = findWord("word", new File("src/files/txt5.txt"));
            if (!result.isFind) System.out.println("Слово не найдено\n");
            else System.out.printf("Слово найдено на позиции %s\n", result.position);
            //Четвертое. Ищет первое вхождение в папке, если есть, сообщает - где нашёл.
            result = findWord("word", "src/files");
            if (!result.isFind) System.out.println("Слово не найдено\n");
            else System.out.printf("Слово найдено в файле %s на позиции %d\n", result.fileName.getName(), result.position);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    private static void gluingFiles(File file, File file1, File gluedFile) throws IOException {
        int b, j = 0;
        boolean f1 = gluedFile.getAbsolutePath().equals(file.getAbsolutePath());
        boolean f2 = gluedFile.getAbsolutePath().equals(file1.getAbsolutePath());
        if (f1 && f2)
            return; //Для слияния всех файлов из папки, а то целевой попадётся и зациклится. Да и пользователи бывают невыспавшиеся.
        if (f2) {
            File tmpFile = new File(file1.getAbsolutePath() + new Date().getTime() + ".tmp");
            if (!file1.renameTo(tmpFile)) throw new IOException("Can't rename file: " + file1.getAbsolutePath());
            file1 = tmpFile;
        }
        if (!f1) {
            //Либо сделать исключение, что целевой файл есть и мы не будем его затирать, несмотря на то, что его содержимое будет добавлено.
            //Либо добавить параметр на перезапись. Либо затереть. В рамках ДЗ я за третье, но параметр универсальнее.
            if (gluedFile.exists())
                if (!gluedFile.delete()) throw new IOException("Can't delete " + gluedFile.getAbsolutePath());
        } else j = 1;
        FileOutputStream write = new FileOutputStream(gluedFile, j == 1);
        do {
            FileInputStream read = new FileInputStream((j == 0) ? file : file1);
            while ((b = read.read()) != -1) {
                write.write(b);
            }
            read.close();
            j++;
        } while (j < 2);
        write.close();
        if (f2) {
            if (!file1.delete()) throw new IOException("Can't delete file" + file1.getAbsolutePath());
        }
    }

    //Слить все файлы в папке в один.
    private static void gluingFiles(File folder, File gluedFile) throws IOException {
        String[] list = folder.list();
        for (int i = 0; i < list.length; i++)
            gluingFiles(gluedFile, new File(folder.getAbsolutePath() + "/" + list[i]), gluedFile);
    }

    private static Result findWord(String word, File file) throws IOException {
        Result result = new Result();
        FileInputStream read = new FileInputStream(file);
        int j = 1, sym;
        while ((sym = read.read()) != -1) {
            if (sym == (int) word.charAt(0)) {
                //Читать сразу количество символов в слове нельзя, можем пропустить начало другого вхождения (например w1word)
                for (int i = 1; i < word.length(); i++) {
                    j++;
                    if (read.read() != (int) (word.charAt(i))) {
                        result.isFind = false;
                        break;
                    }
                    result.isFind = true;
                }
                if (result.isFind) {
                    result.fileName = file;
                    result.position = ++j - word.length();
                    read.close();
                    return result;
                }
            }
            j++;
        }
        read.close();
        return result;
    }


    private static Result findWord(String word, String folder) throws IOException {
        Result result = new Result();
        String[] list = new File(folder).list();
        for (int i = 0; i < list.length; i++) {
            result = findWord(word, new File(folder, list[i]));
            if (result.isFind) return result;
        }
        return result;
    }


    //В случае обнаружения заодно передадим и имя файла, и позицию, где слово нашлось.
    static class Result {
        boolean isFind;
        File fileName;
        int position;

        Result() {
            isFind = false;
        }
    }
}