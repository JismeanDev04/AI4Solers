Tên đề tài: AI4Solers.
Ý tưởng: Ứng dụng các model AI xử lý hình ảnh hiện nay để phục vụ nhu cầu cá nhân.
Mục tiêu: Cho người dùng trải nghiệm việc tạo hình ảnh một cách nhanh chóng, thuận tiện. Trở thành 1 ứng dụng tập hợp nhiều chức năng của AI.

Người dùng hướng tới: Những cá nhân có nhu cầu sử dụng công cụ AI để xử lý hình ảnh cá nhân theo như ý muốn.

Các chức năng gồm:

    • Tạo ảnh bằng prompt
    • Xóa phông nền     
    • Thay thế phông nền
    • Lưu hình ảnh

Project Structure:


com.example.ai4solers
├── app
│   ├── AI4SolersApp.kt         
│   └── di                      
│       ├── AppModule.kt        
│       ├── NetworkModule.kt    
│       ├── DatabaseModule.kt   
│       └── RepositoryModule.kt 
│
├── core                      
│   ├── common                  
│   ├── ui                    
│   └── util                    
│
├── data                      
│   ├── local                   
│   │   ├── db                 
│   │   │   ├── AppDatabase.kt
│   │   │   ├── HistoryDao.kt
│   │   │   └── HistoryEntity.kt
│   │   └── storage            
│   │       └── LocalStorageManager.kt
│   │
│   ├── remote                
│   │   ├── clipdrop           
│   │   │   ├── ClipDropApi.kt
│   │   │   └── dto            
│   │   └── removebg            
│   │       ├── RemoveBgApi.kt
│   │       └── dto
│   │
│   └── repository             
│       ├── AIProcessingRepositoryImpl.kt 
│       └── HistoryRepositoryImpl.kt     
│
├── domain                    
│   ├── model                  
│   ├── repository              
│   └── usecase                 
│       ├── GenerateImageUseCase.kt    
│       ├── RemoveBackgroundUseCase.kt  
│       ├── ReplaceBackgroundUseCase.kt
│       └── SaveResultUseCase.kt       
│
└── ui                        
├── navigation             
│
├── feature_home          
│   ├── HomeScreen.kt
│   └── HomeViewModel.kt
│
├── feature_tools          
│   ├── text_to_image      
│   │   ├── TextToImageScreen.kt
│   │   └── TextToImageViewModel.kt
│   ├── bg_remover         
│   │   ├── RemoveBgScreen.kt
│   │   └── RemoveBgViewModel.kt
│   └── bg_replacer        
│       ├── ReplaceBgScreen.kt
│       └── ReplaceBgViewModel.kt
│
└── feature_history      
├── HistoryScreen.kt    
└── HistoryViewModel.kt

Architechture&Techstack: MVVM, Jetpack Compose, Kotlin, Retrofit, Room Database.
