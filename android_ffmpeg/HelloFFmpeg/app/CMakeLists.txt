cmake_minimum_required(VERSION 3.4.1)
add_library( # Sets the name of the library.
             HelloFFmpegCfg

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/jni/ffmpeg.c )
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )
find_library( android-lib
              android )

set(distribution_DIR ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})

add_library( avutil-55
             SHARED
             IMPORTED )
set_target_properties( avutil-55
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/libavutil-55.so)

add_library( swresample-2
             SHARED
             IMPORTED )
set_target_properties( swresample-2
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/libswresample-2.so)

add_library( avfilter-6
             SHARED
             IMPORTED )
set_target_properties( avfilter-6
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/libavfilter-6.so)

add_library( avformat-57
             SHARED
             IMPORTED )
set_target_properties( avformat-57
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/libavformat-57.so)

add_library( swscale-4
             SHARED
             IMPORTED )
set_target_properties( swscale-4
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/libswscale-4.so)


add_library( avcodec-57
             SHARED
             IMPORTED )
set_target_properties( avcodec-57
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/libavcodec-57.so)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
include_directories(src/main/cpp)
include_directories(src/main/jniLibs/include)

target_link_libraries(HelloFFmpegCfg
                      avcodec-57
                      avfilter-6
                      avformat-57
                      avutil-55
                      swresample-2
                      swscale-4
                      ${log-lib}
                      ${android-lib})