package utils

trait TestReaderAbility {
    List<String> testInput(String fileName) {
        return TestFileReader.readFile(fileName)
    }
}