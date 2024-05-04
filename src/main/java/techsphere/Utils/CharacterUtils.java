package techsphere.Utils;

public class CharacterUtils {

    public static String removeSpecialCharacters(String input) {
        // Sử dụng biểu thức chính quy để chỉ định các ký tự đặc biệt cần loại bỏ
        String regex = "[^a-zA-Z0-9.\\s]";
        return input.replaceAll(regex, "");
    }
}
