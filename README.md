# 🚀 AI4Solers - Trợ lý AI Toàn năng cho Hình ảnh & Trò chuyện

**AI4Solers** là một ứng dụng Android mang sức mạnh của AI trực tiếp lên thiết bị di động. Được xây dựng hoàn toàn bằng Kotlin và Jetpack Compose, kết hợp các tính năng tạo ảnh AI, xử lý phông nền và trợ lý trò chuyện thông minh vào một trải nghiệm duy nhất.

## ✨ Tính năng Nổi bật

* **🎨 Tạo ảnh từ văn bản (Text-to-Image)**
* **✂️ Xóa nền AI (Background Remover)**
* **🪄 Thay nền (Background Replacer)**
* **💬 Trợ lý Gemini 2.5 Flash**
* **📚 Lịch sử Ngoại tuyến (Offline History)**

## 💡 Cảm hứng & Ứng dụng Thực tế

AI4Solers không chỉ là một bản demo công nghệ; nó được thiết kế cho các ứng dụng thực tế, tích hợp sẵn các mẫu prompt cho các tình huống cụ thể:
* **Tài liệu Giáo dục Trực quan:** Hoàn hảo để tạo ra các bối cảnh sinh động, chi tiết nhằm hỗ trợ học sinh cấp 2 luyện kỹ năng nói tiếng Anh.
* **Minh họa Truyện & Dự án Văn học:** Tạo ra các concept art mang tính đồng nhất và kỳ ảo, ví dụ như thiết kế storyboard nhiều cảnh cho truyện Lọ Lem.
* **Thương mại điện tử & Marketing:** Nhanh chóng thay thế nền sản phẩm để có được những bức ảnh chuẩn studio chuyên nghiệp.

## 🛠️ Công nghệ & Kiến trúc

Dự án sử dụng **Clean Architecture** và mô hình **MVVM** để đảm bảo khả năng mở rộng, dễ bảo trì và phân tách trách nhiệm rõ ràng giữa các tầng.

**Các công nghệ cốt lõi:**
* **Giao diện:** [Jetpack Compose](https://developer.android.com/jetpack/compose) 
* **Điều hướng:** Type-Safe [Navigation Compose](https://developer.android.com/guide/navigation/design/type-safe) (Navigation 3)
* **(DI):** [Dagger Hilt](https://dagger.dev/hilt/)
* **Xử lý Bất đồng bộ:** Kotlin Coroutines & Flow
* **Mạng:** [Retrofit](https://square.github.io/retrofit/) & OkHttp 
* **Lưu trữ Cục bộ:** [Room Database](https://developer.android.com/training/data-storage/room) & Android FileSystem / FileProvider
* **Tải ảnh (Image Loading):** [Coil](https://coil-kt.github.io/coil/)
* **AI SDK:** Google Generative AI SDK (Gemini 2.5 Flash)

## 📸 Ảnh chụp màn hình

 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/f32d5c78-65bb-43c3-8fcb-57c38f87fc6d" /> 
 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/e7182b68-81e7-4d11-b41e-2c7337c057c5" /> 
 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/d6a67095-d607-4932-97b7-c9f595f3ab36" /> 
 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/34c9c21f-2366-4151-8c3b-b0f3319b19b1" />
 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/df0d7ca0-f1c2-431e-89d2-fd4ec2f4a025" />
 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/623cf217-09e7-4397-b4cf-271be7b700dd" />
 <img width="200" height="400" alt="image" src="https://github.com/user-attachments/assets/1adbe48e-83e4-48c5-8e73-c1231b908870" />

## 🚀 Hướng dẫn Cài đặt

Để chạy dự án này trên máy của bạn, bạn cần tự cung cấp API key.

1.  Clone repository về máy:
    ```bash
    git clone https://github.com/JismeanDev04/AI4Solers.git
    ```
2.  Mở dự án bằng Android Studio.
3.  Tạo một file tên là `local.properties` ở thư mục gốc của dự án (nếu chưa có).
4.  Thêm API keys của bạn vào file `local.properties` theo định dạng sau:
    ```properties
    GEMINI_API_KEY=""
    CLIPDROP_API_KEY=""
    ```
5.  Đồng bộ (Sync) dự án với Gradle. Hệ thống `BuildConfig` sẽ tự động tạo và nhúng các key này vào ứng dụng một cách an toàn.
6.  Build và chạy ứng dụng trên máy ảo (Emulator) hoặc thiết bị thật.

## 🔮 Định hướng Phát triển (Roadmap)

* [ ] Hoàn thiện màn hình Chi tiết Lịch sử (Xem toàn bộ prompt & chức năng chia sẻ ảnh).
* [ ] Tích hợp thư viện Markdown (`Markwon`) để định dạng văn bản phản hồi từ Gemini.
* [ ] Tích hợp thêm các mô hình AI khác vào tab "Models".
* [ ] Thêm tính năng Zoom (Pinch-to-zoom) để xem kỹ chi tiết các bức ảnh đã tạo.

---
*Được phát triển với ❤️ bởi TrietSWE*
