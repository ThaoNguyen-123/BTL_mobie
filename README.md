# XÂY DỰNG ỨNG DỤNG CÂU HỎI TRẮC NGHIỆM QUIZZ GAME TRÊN NỀN TẢNG ANDROID

**Sinh viên thực hiện:**
*   Phạm Thị Kim Ngân - MSSV: 12423071 - Lớp: 12523T.1
*   Chu Thị Thảo Nguyên - MSSV: 12423052 - Lớp: 12523T.1

**Giảng viên hướng dẫn:**
* ThS.Bùi Đức Thọ

---

## GIỚI THIỆU ĐỀ TÀI
Trong bối cảnh hội nhập hiện nay, việc trau dồi kiến thức ở nhiều lĩnh vực như Khoa học, Văn hóa, Nghệ thuật hay Thể thao là nhu cầu thiết yếu. Thay vì tiếp thu thụ động, hình thức trả lời trắc nghiệm giúp kích thích não bộ và tăng khả năng ghi nhớ.
Dưới sự bùng nổ của công nghệ số, các bài kiểm tra đã nhanh chóng chuyển mình thành các ứng dụng di động, trở thành công cụ học tập mọi lúc, mọi nơi. Xuất phát từ nhu cầu đó, nhóm đã xây dựng ứng dụng Quizz Game trên nền tảng Android, kết hợp hệ quản trị cơ sở dữ liệu SQLite để mang lại trải nghiệm giải trí và giáo dục hoàn toàn ngoại tuyến (offline).

## BÀI TOÁN
Người dùng cần một môi trường để kiểm tra kiến thức nhanh chóng mà không bị rào cản bởi các bước thiết lập rườm rà.
Hệ thống cần quản lý:
*   Ngân hàng câu hỏi theo từng chủ đề.
*   Thông tin tài khoản, điểm số và số mạng của người chơi.
*   Lịch sử làm bài để rút kinh nghiệm.
Do đó, việc xây dựng một hệ thống ứng dụng di động có khả năng lưu trữ cục bộ, trộn đề ngẫu nhiên và phản hồi kết quả tức thì là vô cùng cần thiết.

## MỤC TIÊU ĐỀ TÀI
Xây dựng ứng dụng Android hỗ trợ:
*   Thiết kế giao diện (UI/UX) trực quan, tối giản.
*   Phát triển kho tàng câu đố mang tính tư duy, logic cao.
*   Đảm bảo ứng dụng vận hành mượt mà, tối ưu ngoại tuyến không cần internet.
*   Sử dụng SQLite để quản lý kho dữ liệu câu hỏi và bảng xếp hạng nội bộ.
*   Tối ưu hóa luồng trải nghiệm, hỗ trợ sinh định danh tự động để người chơi tham gia nhanh chóng.

## PHẠM VI HỆ THỐNG
**Đối tượng nghiên cứu:** 
*   Ứng dụng di động trên nền tảng Android (Smartphone).
*   Cơ chế quản lý dữ liệu cục bộ bằng SQLite.

**Giới hạn hệ thống:**
*   Chỉ hoạt động ngoại tuyến (Offline) trực tiếp trên thiết bị.
*   Không sử dụng hệ thống máy chủ internet.

## CÔNG NGHỆ SỬ DỤNG
*   **Ngôn ngữ lập trình:** Java
*   **Thiết kế giao diện:** XML
*   **Cơ sở dữ liệu:** SQLite
*   **IDE:** Android Studio
*   **Phương pháp thiết kế:** Lập trình hướng đối tượng (OOP)

## CHỨC NĂNG HỆ THỐNG

**1. Quản lý thông tin cá nhân**
*   **Xem thông tin:** Theo dõi tên hiển thị, số dư tiền vàng, số mạng (lượt chơi).
*   **Đổi điểm thưởng:** Dùng tiền vàng đổi vật phẩm hoặc lượt chơi.

**2. Chơi game trắc nghiệm**
*   **Chọn chủ đề:** Khoa học, Văn hóa, Nghệ thuật, Thể thao.
*   **Trả lời có thời gian:** Chọn đáp án A, B, C, D trong thời gian đếm ngược.
*   **Quyền trợ giúp:** 50/50, Nhân đôi điểm (X2), Tạm dừng (PAUSE).
*   **Nhận kết quả tức thì:** Đổi màu đáp án đúng/sai, tự động cộng điểm hoặc trừ mạng.

**3. Tra cứu kết quả**
*   **Xem tổng kết:** Hiển thị Điểm số, Tổng số câu, Số câu đúng, Số câu sai.
*   **Xem chi tiết lịch sử:** Đối chiếu đáp án đã chọn với đáp án chuẩn.

**4. Quản lý tài khoản hệ thống**
*   Đăng nhập, Đăng ký và Đăng xuất.

## CƠ SỞ DỮ LIỆU
Hệ thống sử dụng SQLite với các đối tượng chính:

**Bảng Câu hỏi (Question)**
*   questionText (Nội dung câu hỏi)
*   optionA, optionB, optionC, optionD (Các đáp án)
*   correctAnswer (Đáp án đúng)
*   topic (Chủ đề)

**Bảng Người chơi**
*   username (Tên đăng nhập)
*   password (Mật khẩu)
*   coins (Số tiền vàng)
*   hearts (Số mạng)

**Bảng Lịch sử**
*   questionText (Câu hỏi)
*   selectedAnswer (Đáp án đã chọn)
*   correctAnswer (Đáp án chuẩn)
*   isCorrect (Trạng thái Đúng/Sai)
*   topic (Chủ đề)

## KẾT QUẢ ĐẠT ĐƯỢC
*   ✔ Hoàn thiện 100% luồng chức năng của phân hệ Người chơi (Đăng nhập, thi trắc nghiệm, tra cứu).
*   ✔ Thiết kế thành công giao diện (UI/UX) trực quan, mượt mà.
*   ✔ Xây dựng thành công hệ thống logic: đếm ngược thời gian, xử lý trợ giúp, tính điểm chính xác.
*   ✔ Nắm vững lập trình hướng đối tượng (OOP) với Java và cơ sở dữ liệu SQLite.

## HƯỚNG PHÁT TRIỂN
*   **Phát triển tính năng Online:** Xây dựng Bảng xếp hạng toàn cầu (Global Leaderboard) và chế độ thi đấu đối kháng (PvP).
*   **Đa dạng hóa nội dung:** Thêm câu hỏi kèm hình ảnh, video, âm thanh.
*   **Xây dựng phân hệ Admin:** Cho phép Thêm, Sửa, Xóa câu hỏi và quản lý người chơi mà không cần can thiệp mã nguồn.

## TÀI LIỆU THAM KHẢO
1. K. CNTT, Lập trình Mobile cơ bản, Hưng Yên: Đại học SPKT Hưng Yên, 2023.
2. K. CNTT, Công nghệ phần mềm, Hưng Yên: Đại học SPKT Hưng Yên, 2009.
3. K. CNTT, Kiểm thử phần mềm, Hưng Yên: Đại học SPKT Hưng Yên, 2016.
