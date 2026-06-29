package com.example.btl.data;

import com.example.btl.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    public static final String TOPIC_SCIENCE  = "Khoa học";
    public static final String TOPIC_CULTURE  = "Văn hóa";
    public static final String TOPIC_ART      = "Nghệ thuật";
    public static final String TOPIC_SPORT    = "Thể thao";

    public static List<Question> getQuestions(String topic) {
        switch (topic) {
            case TOPIC_SCIENCE:  return getScienceQuestions();
            case TOPIC_CULTURE:  return getCultureQuestions();
            case TOPIC_ART:      return getArtQuestions();
            case TOPIC_SPORT:    return getSportQuestions();
            default:             return getScienceQuestions();
        }
    }

    // ── KHOA HỌC (10 câu) ────────────────────────────────────────────────────
    private static List<Question> getScienceQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question(
                "Hành tinh nào quay nhanh nhất, hoàn thành một vòng quay chỉ trong 10 giờ?",
                "Sao Mộc", "Sao Hỏa", "Sao Kim", "Sao Thiên Vương",
                0, TOPIC_SCIENCE));
        list.add(new Question(
                "Có bao nhiêu xương trong cơ thể người trưởng thành?",
                "106", "206", "306", "406",
                1, TOPIC_SCIENCE));
        list.add(new Question(
                "Tốc độ ánh sáng trong chân không xấp xỉ bao nhiêu km/s?",
                "150.000", "300.000", "450.000", "600.000",
                1, TOPIC_SCIENCE));
        list.add(new Question(
                "Nguyên tố nào chiếm tỉ lệ cao nhất trong vỏ Trái Đất?",
                "Silic", "Sắt", "Oxy", "Nhôm",
                2, TOPIC_SCIENCE));
        list.add(new Question(
                "DNA viết tắt của từ nào?",
                "Deoxyribose Nuclear Acid",
                "Deoxyribonucleic Acid",
                "Deoxyribose Nucleotide Acid",
                "Double Nuclear Acid",
                1, TOPIC_SCIENCE));
        list.add(new Question(
                "Hành tinh nào lớn nhất trong Hệ Mặt Trời?",
                "Sao Thổ", "Sao Thiên Vương", "Sao Mộc", "Sao Hải Vương",
                2, TOPIC_SCIENCE));
        list.add(new Question(
                "Vitamin C còn có tên gọi khoa học là gì?",
                "Retinol", "Calciferol", "Axit ascorbic", "Tocopherol",
                2, TOPIC_SCIENCE));
        list.add(new Question(
                "Đơn vị đo áp suất khí quyển là gì?",
                "Newton", "Pascal", "Joule", "Watt",
                1, TOPIC_SCIENCE));
        list.add(new Question(
                "Bộ phận nào của não người chịu trách nhiệm về trí nhớ?",
                "Tiểu não", "Đồi thị", "Hải mã", "Thuỳ trán",
                2, TOPIC_SCIENCE));
        list.add(new Question(
                "Kim loại nào lỏng ở nhiệt độ phòng?",
                "Nhôm", "Thủy ngân", "Đồng", "Chì",
                1, TOPIC_SCIENCE));
        return list;
    }

    // ── VĂN HÓA (10 câu) ─────────────────────────────────────────────────────
    private static List<Question> getCultureQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question(
                "Tháp nghiêng Pisa là biểu tượng của nước nào?",
                "Đức", "Thụy Sĩ", "Italy", "Romania",
                2, TOPIC_CULTURE));
        list.add(new Question(
                "Loại chim nào là biểu tượng của đất nước Đan Mạch?",
                "Đại bàng", "Thiên nga", "Bồ câu", "Quạ",
                1, TOPIC_CULTURE));
        list.add(new Question(
                "Cây chua me đất là biểu tượng của xứ sở nào?",
                "Scotland", "Ireland", "Wales", "England",
                1, TOPIC_CULTURE));
        list.add(new Question(
                "Chuột túi là biểu tượng của quốc gia nào?",
                "New Zealand", "Nam Phi", "Canada", "Úc",
                3, TOPIC_CULTURE));
        list.add(new Question(
                "Pháp có ba công trình biểu tượng nổi tiếng, đó là tháp Eiffel, nhà thờ Đức Bà Paris, và:",
                "Khải Hoàn Môn", "Bảo tàng Louvre", "Cung điện Versailles", "Cầu Mirabeau",
                0, TOPIC_CULTURE));
        list.add(new Question(
                "Ngày Tết Nguyên Đán là ngày đầu tiên của tháng mấy âm lịch?",
                "Tháng Chạp", "Tháng Giêng", "Tháng Hai", "Tháng Ba",
                1, TOPIC_CULTURE));
        list.add(new Question(
                "Vạn Lý Trường Thành nằm ở quốc gia nào?",
                "Nhật Bản", "Hàn Quốc", "Trung Quốc", "Mông Cổ",
                2, TOPIC_CULTURE));
        list.add(new Question(
                "Lễ hội Oktoberfest nổi tiếng diễn ra ở đâu?",
                "Vienna – Áo", "Munich – Đức", "Prague – Séc", "Zurich – Thụy Sĩ",
                1, TOPIC_CULTURE));
        list.add(new Question(
                "Quốc kỳ Nhật Bản có hình gì trên nền trắng?",
                "Hoa anh đào", "Chim hạc", "Mặt trời đỏ", "Ngọn núi Fuji",
                2, TOPIC_CULTURE));
        list.add(new Question(
                "Chùa Một Cột nằm ở thành phố nào của Việt Nam?",
                "Hội An", "Huế", "Hà Nội", "TP.HCM",
                2, TOPIC_CULTURE));
        return list;
    }

    // ── NGHỆ THUẬT (10 câu) ───────────────────────────────────────────────────
    private static List<Question> getArtQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question(
                "Tác phẩm 'Mona Lisa' do ai sáng tác?",
                "Michelangelo", "Raphael", "Leonardo da Vinci", "Caravaggio",
                2, TOPIC_ART));
        list.add(new Question(
                "Bức tranh 'Đêm đầy sao' (The Starry Night) là của họa sĩ nào?",
                "Claude Monet", "Vincent van Gogh", "Pablo Picasso", "Salvador Dalí",
                1, TOPIC_ART));
        list.add(new Question(
                "Nhạc cụ nào có số dây nhiều nhất?",
                "Guitar", "Đàn tranh", "Đàn harp", "Đàn piano",
                2, TOPIC_ART));
        list.add(new Question(
                "Vũ điệu Flamenco xuất xứ từ đâu?",
                "Bồ Đào Nha", "Mexico", "Tây Ban Nha", "Argentina",
                2, TOPIC_ART));
        list.add(new Question(
                "Opera 'La Traviata' do nhạc sĩ nào sáng tác?",
                "Mozart", "Verdi", "Puccini", "Beethoven",
                1, TOPIC_ART));
        list.add(new Question(
                "Phong trào nghệ thuật 'Ấn tượng' xuất hiện vào thế kỷ mấy?",
                "Thế kỷ XVII", "Thế kỷ XVIII", "Thế kỷ XIX", "Thế kỷ XX",
                2, TOPIC_ART));
        list.add(new Question(
                "Nhạc cụ dân tộc nào là biểu tượng của Việt Nam?",
                "Đàn bầu", "Đàn tỳ bà", "Đàn nhị", "Đàn nguyệt",
                0, TOPIC_ART));
        list.add(new Question(
                "Bảo tàng nghệ thuật Louvre nằm ở thành phố nào?",
                "Rome", "Madrid", "Paris", "Amsterdam",
                2, TOPIC_ART));
        list.add(new Question(
                "'Giao hưởng số 9' là tác phẩm nổi tiếng của ai?",
                "Bach", "Brahms", "Beethoven", "Schubert",
                2, TOPIC_ART));
        list.add(new Question(
                "Phong trào Cubism do ai khởi xướng?",
                "Henri Matisse", "Pablo Picasso", "Georges Braque", "Salvador Dalí",
                1, TOPIC_ART));
        return list;
    }

    // ── THỂ THAO (10 câu) ─────────────────────────────────────────────────────
    private static List<Question> getSportQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question(
                "Môn thể thao nào sử dụng vợt và quả cầu lông?",
                "Tennis", "Cầu lông", "Bóng bàn", "Squash",
                1, TOPIC_SPORT));
        list.add(new Question(
                "World Cup bóng đá được tổ chức mấy năm một lần?",
                "2 năm", "3 năm", "4 năm", "5 năm",
                2, TOPIC_SPORT));
        list.add(new Question(
                "Môn thể thao nào được mệnh danh là 'Vua thể thao'?",
                "Bóng rổ", "Bóng đá", "Tennis", "Bơi lội",
                1, TOPIC_SPORT));
        list.add(new Question(
                "Đội tuyển bóng đá nào giành nhiều chức vô địch World Cup nhất?",
                "Đức", "Argentina", "Brazil", "Pháp",
                2, TOPIC_SPORT));
        list.add(new Question(
                "Cự ly chạy Marathon là bao nhiêu km?",
                "40 km", "42,195 km", "45 km", "50 km",
                1, TOPIC_SPORT));
        list.add(new Question(
                "Môn thể thao nào cần dùng gậy và bóng nhỏ trên sân cỏ với 18 hố?",
                "Hockey", "Polo", "Golf", "Cricket",
                2, TOPIC_SPORT));
        list.add(new Question(
                "Olympic đầu tiên của thời hiện đại được tổ chức tại đâu?",
                "Paris", "London", "Athens", "Rome",
                2, TOPIC_SPORT));
        list.add(new Question(
                "Cầu thủ nào được mệnh danh là 'El Clásico' với 6 lần giành Quả bóng vàng (tính đến 2020)?",
                "Cristiano Ronaldo", "Ronaldinho", "Lionel Messi", "Neymar",
                2, TOPIC_SPORT));
        list.add(new Question(
                "Trong bóng rổ, rổ cao bao nhiêu mét so với mặt sàn?",
                "2,8 m", "3,05 m", "3,5 m", "4 m",
                1, TOPIC_SPORT));
        list.add(new Question(
                "Môn võ thuật nào có nguồn gốc từ Hàn Quốc?",
                "Karate", "Judo", "Taekwondo", "Kung Fu",
                2, TOPIC_SPORT));
        return list;
    }
}
