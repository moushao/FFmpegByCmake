# 设置cmake最低版本
cmake_minimum_required(VERSION 3.4.1)

             
# 添加自身的jni库 native-lib
add_library( native-lib

             SHARED
             
              src/main/cpp/cmdutils.c
                            src/main/cpp/ffmpeg.c
                            src/main/cpp/ffmpeg_filter.c
                            src/main/cpp/ffmpeg_opt.c
              src/main/cpp/native-lib.cpp 
           
             )  

# 设置路径
set(distribution_DIR ${CMAKE_SOURCE_DIR}/../../../../libs)

# 加载头文件
include_directories(src/main/cpp/include

                     E:/Workspace/OtherDemo/FFmpeg4Android-master/ffmpeg-3.2.5
)


# 加载ffmpeg库
add_library( ffmpeg
             SHARED
             IMPORTED )
             
set_target_properties( ffmpeg
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libffmpeg.so )


           


# 查找Android存在的库
find_library( log-lib

              log )
              

# 链接库文件
target_link_libraries(
                       native-lib
                       
                       #android c
                       -landroid
                       
                       # ffmpeg库
                       ffmpeg

                        ${log-lib} )