# OOP Lab 6 - Quản lý thiết bị và phiếu mượn

Bài Java cơ bản: lớp trừu tượng, kế thừa, đa hình, interface, kết tập, hợp thành và ngoại lệ. Dùng ArrayList, vòng lặp for, if và double.

## Chạy chương trình

Mở `src/lab6/Main.java` trong IDE và bấm Run (JDK 17 trở lên).

Hoặc chạy trong PowerShell tại thư mục dự án:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -encoding UTF-8 -d out src/lab6/*.java
java -cp out lab6.Main
```

Main tạo dữ liệu sẵn, minh họa mượn–trả, đủ 7 tình huống lỗi của đề và in phiếu trả muộn/chưa trả. Không cần nhập bàn phím.

## Các lớp chính

- Device, TeachingDevice và 3 lớp thiết bị: kế thừa, đa hình tính bảo trì.
- ClassRoom: kết tập danh sách thiết bị.
- IBorrower, Lecturer, TeachingAssistant: người mượn, giới hạn 3/2 thiết bị.
- BorrowingSlip, BorrowItem: phiếu và các dòng mượn.
- Các lớp ngoại lệ riêng giữ theo yêu cầu đề.

## Quy ước minh họa

| Thiết bị | Ngày tối đa | Tiết tối đa | Bảo trì/năm | Phí muộn/ngày |
| --- | ---: | ---: | ---: | ---: |
| Điều khiển điều hòa | 2 | 5 | 5% giá mua | 10.000 |
| Micro không dây | 1 | 3 | 10% giá mua | 20.000 |
| Điều khiển máy chiếu | 2 | 5 | 6% giá mua | 15.000 |

Micro tối đa 1 ngày được đề quy định; các mức còn lại là giả định vì đề chưa nêu rõ.

Hạn trả = ngày mượn + số ngày. Khoảng tiết tính cả hai đầu, áp dụng mỗi ngày mượn. Phí muộn = số ngày trễ × phí/ngày, chỉ tính khi đã trả. Tiền dùng double cho dễ học. Dữ liệu chỉ nằm trong bộ nhớ.

Tiêu đề có nhắc hợp đồng bảo trì nhưng nội dung đề không yêu cầu lớp hợp đồng.
