package utils

class TestFileReader {
    static List<String> readFile(String fileName) {
        return getFile(fileName).readLines()
    }

    private static File getFile(String fileName) {
        return new File("src/test/resources/$fileName")
    }
}
