package com.cookandroid.jarvis_project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.vectormap.GestureType;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapType;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.MapViewInfo;
import com.kakao.vectormap.camera.CameraAnimation;
import com.kakao.vectormap.camera.CameraPosition;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.camera.CameraUpdateFactory;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.mapwidget.InfoWindowOptions;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiLayout;
import com.kakao.vectormap.mapwidget.component.GuiText;
import com.kakao.vectormap.mapwidget.component.Orientation;
import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineLayer;
import com.kakao.vectormap.route.RouteLineOptions;
import com.kakao.vectormap.route.RouteLineSegment;
import com.kakao.vectormap.route.RouteLineStyle;
import com.kakao.vectormap.route.RouteLineStyles;
import com.kakao.vectormap.route.RouteLineStylesSet;

import java.util.Arrays;
import java.util.List;

public class mainmap extends AppCompatActivity {
    MapView mapView;
    RouteLineLayer layer;
    RouteLineStyles styles1;
    RouteLineStyles styles2;
    RouteLineStyles styles3;
    RouteLineStyles styles4;
    RouteLineStylesSet stylesSet;
    List<RouteLineSegment> segments;
    RouteLineOptions options;
    RouteLine routeLine;

    GuiLayout mainLayout;
    GuiLayout mainLayout2;
    GuiText text;
    GuiText text2;
    GuiImage bgImage;
    InfoWindowOptions options1;
    InfoWindowOptions options2;
    LabelStyles style1;
    LabelStyles style2;
    LabelStyles style3;
    LabelStyles style4;
    LabelStyles style5;
    LabelStyles style6;
    LabelStyles style7;
    LabelStyles style8;
    LabelStyles style9;


    Label label1;
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    Label label6;
    Label label7;
    Label label8;
    Label label9;
    CameraUpdate cameraUpdate;
    Button monument;
    Button art;
    Button library;
    Button mechanic;
    Button law;
    Button main_north;
    Button student;
    Button social_science;

    Button bus_route1;
    Button bus_route2;
    Button busmain_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmap);
        monument = (Button)findViewById(R.id.monument);
        art = (Button)findViewById(R.id.art);
        library = (Button)findViewById(R.id.library);
        mechanic = (Button)findViewById(R.id.mechanic);
        law = (Button)findViewById(R.id.law);
        main_north = (Button)findViewById(R.id.main_north);
        student = (Button)findViewById(R.id.student);
        social_science = (Button)findViewById(R.id.social_science);
        bus_route1 = (Button)findViewById(R.id.bus_route1);
        bus_route2 = (Button)findViewById(R.id.bus_route2);
        busmain_back = (Button)findViewById(R.id.busmain_back);
        KakaoMapSdk.init(this, "e0036c32372bfece6a328fdecf90f17c");
        MapView mapView = findViewById(R.id.map_view);
        busmain_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bus_main.class);
                startActivity(intent);
            }
        });
        mapView.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {

            }

            @Override
            public void onMapError(Exception error) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, new KakaoMapReadyCallback() {
            @Override
            public void onMapReady(KakaoMap kakaoMap) {
                LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("null",LatLng.from(35.1420836,126.9278376)).setStyles(styles));
                LabelStyles styles1 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1432463,126.9295868)).setStyles(styles));
                LabelStyles styles2 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1416824,126.931715)).setStyles(styles));
                LabelStyles styles3 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label3 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1390926,126.9335192)).setStyles(styles));
                LabelStyles styles4 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(18));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label4 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1405812,126.935049)).setStyles(styles));
                LabelStyles styles5 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label5 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1437982,126.9342897)).setStyles(styles));
                LabelStyles styles6 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label6 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1435061,126.933088)).setStyles(styles));
                LabelStyles styles7 = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                Label label7 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.145378,126.934500)).setStyles(styles));
            }
            @Override
            public LatLng getPosition() {
                // 지도 시작 시 위치 좌표를 설정
                return LatLng.from(35.14202914245671 ,126.93165818939171);
            }

            @Override
            public int getZoomLevel() {
                // 지도 시작 시 확대/축소 줌 레벨 설정
                return 16;
            }

            @Override
            public MapViewInfo getMapViewInfo() {
                // 지도 시작 시 App 및 MapType 설정
                return MapViewInfo.from("openmap",MapType.NORMAL);
            }

            @Override
            public String getViewName() {
                // KakaoMap 의 고유한 이름을 설정
                return "MyFirstMap1";
            }

            @Override
            public boolean isVisible() {
                // 지도 시작 시 visible 여부를 설정
                return true;
            }

            @Override
            public String getTag() {
                // KakaoMap 의 tag 을 설정
                return "FirstMapTag";
            }

        });


        monument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {

                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.1426156, 126.9280382));
                        kakaoMap.moveCamera(cameraUpdate);
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("null",LatLng.from(35.1420836,126.9278376)).setStyles(styles));
                    }
                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                });
            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1432463,126.9295868)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from(  35.1436099,126.9300082);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1416824,126.931715)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1416824,126.931715);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });

        mechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1390926,126.9335192)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1390926,126.9335192);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });

        law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(18));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1405812,126.935049)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1405812,126.935049);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });

        main_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1437982,126.9342897)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1437982,126.9342897);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.1435061,126.933088)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1441139,126.9331041);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });

        social_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        LabelStyles styles = LabelStyles.from("myStyleId", LabelStyle.from(R.drawable.redmarker).setZoomLevel(16));
                        styles = kakaoMap.getLabelManager().addLabelStyles(styles);
                        Label label = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from(null,LatLng.from(35.145378,126.934500)).setStyles(styles));
                    }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from(35.1458161,126.9342721 );
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });
        bus_route1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        mainroute(kakaoMap);

                    }

                        void mainroute(KakaoMap kakaoMap){
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.140871154694516, 126.93212551650666), 15);
                            kakaoMap.moveCamera(cameraUpdate);
                            mainLayout = new GuiLayout(Orientation.Horizontal);
                            mainLayout.setPadding(20, 20, 20, 18);
                            mainLayout2 = new GuiLayout(Orientation.Horizontal);
                            mainLayout2.setPadding(20, 20, 20, 18);
                            bgImage = new GuiImage(R.drawable.window_body, true);
                            bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                            mainLayout.setBackground(bgImage);
                            mainLayout2.setBackground(bgImage);
                            text = new GuiText("출발");
                            text.setTextSize(30);
                            text2 = new GuiText("도착");
                            text2.setTextSize(30);
                            mainLayout.addView(text);
                            mainLayout2.addView(text2);
                            options1 = InfoWindowOptions.from(LatLng.from(35.14189853362754, 126.92792189116324)); // it융합대학쪽
                            options1.setZOrder(2);
                            options1.setBody(mainLayout);
                            options2 = InfoWindowOptions.from(LatLng.from(35.14380396697894, 126.9341778794842)); // 체대
                            options2.setZOrder(-1);
                            options2.setBody(mainLayout2);
                            kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                            kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                            style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                            style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                            label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1", LatLng.from(35.14189853362754, 126.92792189116324)).setStyles(style1));

                            style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                            label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2", LatLng.from(35.14380396697894, 126.9341778794842)).setStyles(style2));

                            style3 = LabelStyles.from("myStyleId3", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style3 = kakaoMap.getLabelManager().addLabelStyles(style3);
                            label3 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker3", LatLng.from(35.14200210, 126.9277764)).setStyles(style3));

                            style4 = LabelStyles.from("myStyleId4", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style4 = kakaoMap.getLabelManager().addLabelStyles(style4);
                            label4 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker4", LatLng.from(35.1434435, 126.9303785)).setStyles(style4));

                            style5 = LabelStyles.from("myStyleId5", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style5 = kakaoMap.getLabelManager().addLabelStyles(style5);
                            label5 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker5", LatLng.from(35.1423265,126.9316716)).setStyles(style5));

                            style6 = LabelStyles.from("myStyleId6", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style6 = kakaoMap.getLabelManager().addLabelStyles(style6);
                            label6 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker6", LatLng.from(35.1391864, 126.9335616)).setStyles(style6));

                            style7 = LabelStyles.from("myStyleId7", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style7 = kakaoMap.getLabelManager().addLabelStyles(style7);
                            label7 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker7", LatLng.from(35.1407216, 126.9348552)).setStyles(style7));

                            style8 = LabelStyles.from("myStyleId8", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style8 = kakaoMap.getLabelManager().addLabelStyles(style8);
                            label8 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker8", LatLng.from(35.1438535, 126.9341833)).setStyles(style8));

                            style9 = LabelStyles.from("myStyleId9", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                            style9 = kakaoMap.getLabelManager().addLabelStyles(style9);
                            label9 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker9", LatLng.from(35.1434516, 126.9327900)).setStyles(style9));

                            layer = kakaoMap.getRouteLineManager().getLayer();
                            styles1 = RouteLineStyles.from(RouteLineStyle.from(12, Color.BLACK));
                            styles2 = RouteLineStyles.from(RouteLineStyle.from(12, Color.BLUE).setZoomLevel(10),
                                    RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                            styles3 = RouteLineStyles.from(RouteLineStyle.from(12, Color.BLUE)
                            );
                            styles4 = RouteLineStyles.from(RouteLineStyle.from(12, Color.GREEN));

                            stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                            segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.14189853362754, 126.92792189116324), LatLng.from(35.14180633292038, 126.92824019767217)), stylesSet.getStyles(0)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14180633292038, 126.92824019767217), LatLng.from(35.14169823607418, 126.928352768552981)), stylesSet.getStyles(1)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14169823607418, 126.92835276855298), LatLng.from(35.141783936737426, 126.92847339954558)), stylesSet.getStyles(2)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.141783936737426, 126.92847339954558), LatLng.from(35.14189210175397, 126.92847604824736)), stylesSet.getStyles(3)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14189210175397, 126.92847604824736), LatLng.from(35.143050636977776, 126.92896060997579)), stylesSet.getStyles(2)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.143050636977776, 126.92896060997579), LatLng.from(35.143337077363654, 126.92940204403958)), stylesSet.getStyles(1)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.143337077363654, 126.92940204403958), LatLng.from(35.14347351708268, 126.93155547712338)), stylesSet.getStyles(0)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14347351708268, 126.93155547712338), LatLng.from(35.13917888442402, 126.932192758785)), stylesSet.getStyles(1)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.13917888442402, 126.932192758785), LatLng.from(35.13897196203753, 126.93289519621185)), stylesSet.getStyles(2)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.13897196203753, 126.93289519621185), LatLng.from(35.139197663846645, 126.93355064380877)), stylesSet.getStyles(3)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.139197663846645, 126.93355064380877), LatLng.from(35.139250026689915, 126.93453267949741)), stylesSet.getStyles(0)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.139250026689915, 126.93453267949741), LatLng.from(35.13941908835961, 126.9346367873021)), stylesSet.getStyles(1)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.13941908835961, 126.9346367873021), LatLng.from(35.14031829305526, 126.93481438114026)), stylesSet.getStyles(2)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14031829305526, 126.934814381140265), LatLng.from(35.14077350886935, 126.93486614068226)), stylesSet.getStyles(3)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14077350886935, 126.93486614068226), LatLng.from(35.14091991524317, 126.9347453192389)), stylesSet.getStyles(0)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.14091991524317, 126.9347453192389), LatLng.from(35.141028099345036, 126.93478363903107)), stylesSet.getStyles(1)),
                                    RouteLineSegment.from(Arrays.asList(LatLng.from(35.141028099345036, 126.93478363903107), LatLng.from(35.14380396697894, 126.9341778794842)), stylesSet.getStyles(2)));

                            options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                            routeLine = layer.addRouteLine(options);
                        }
                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1441139,126.9331041);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });
        bus_route2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.start(new MapLifeCycleCallback() {
                    @Override
                    public void onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                    }

                    @Override
                    public void onMapError(Exception error) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                    }
                }, new KakaoMapReadyCallback() {
                    @Override
                    public void onMapReady(KakaoMap kakaoMap) {
                        socialroute(kakaoMap);
                    }
                    void socialroute(KakaoMap kakaoMap){
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.14343756939266, 126.93174480014414 ), 15);
                        kakaoMap.moveCamera(cameraUpdate);
                        mainLayout = new GuiLayout(Orientation.Horizontal);
                        mainLayout.setPadding(20, 20, 20, 18);
                        mainLayout2 = new GuiLayout(Orientation.Horizontal);
                        mainLayout2.setPadding(20, 20, 20, 18);
                        bgImage = new GuiImage(R.drawable.window_body, true);
                        bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                        mainLayout.setBackground(bgImage);
                        mainLayout2.setBackground(bgImage);
                        text = new GuiText("출발");
                        text.setTextSize(30);
                        text2 = new GuiText("도착");
                        text2.setTextSize(30);
                        mainLayout.addView(text);
                        mainLayout2.addView(text2);
                        options1 = InfoWindowOptions.from(LatLng.from(35.14189853362754, 126.92792189116324)); // it융합대학쪽
                        options1.setZOrder(2);
                        options1.setBody(mainLayout);
                        options2 = InfoWindowOptions.from(LatLng.from(35.14539058429644,126.93458537483369)); // 체대
                        options2.setZOrder(-1);
                        options2.setBody(mainLayout2);
                        kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                        kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                        style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                        style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                        label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1", LatLng.from(35.14189853362754, 126.92792189116324)).setStyles(style1));

                        style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                        style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                        label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2", LatLng.from(35.14539058429644,126.93458537483369)).setStyles(style2));

                        style3 = LabelStyles.from("myStyleId3", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                        style3 = kakaoMap.getLabelManager().addLabelStyles(style3);
                        label3 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker3", LatLng.from(35.14200210, 126.9277764)).setStyles(style3));

                        style4 = LabelStyles.from("myStyleId4", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                        style4 = kakaoMap.getLabelManager().addLabelStyles(style4);
                        label4 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker4", LatLng.from(35.1434435, 126.9303785)).setStyles(style4));

                        style5 = LabelStyles.from("myStyleId5", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                        style5 = kakaoMap.getLabelManager().addLabelStyles(style5);
                        label5 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker5", LatLng.from(35.1453475, 126.9341958)).setStyles(style5));

                        layer = kakaoMap.getRouteLineManager().getLayer();
                        styles1 = RouteLineStyles.from(RouteLineStyle.from(12, Color.BLACK));
                        styles2 = RouteLineStyles.from(RouteLineStyle.from(12, Color.BLUE).setZoomLevel(10),
                                RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                        styles3 = RouteLineStyles.from(RouteLineStyle.from(12, Color.BLUE)
                        );
                        styles4 = RouteLineStyles.from(RouteLineStyle.from(12, Color.GREEN));

                        stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                        segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.14189853362754, 126.92792189116324), LatLng.from(35.14180633292038, 126.92824019767217)), stylesSet.getStyles(0)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14180633292038, 126.92824019767217), LatLng.from(35.14169823607418, 126.928352768552981)), stylesSet.getStyles(1)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14169823607418, 126.92835276855298), LatLng.from(35.141783936737426, 126.92847339954558)), stylesSet.getStyles(2)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.141783936737426, 126.92847339954558), LatLng.from(35.14189210175397, 126.92847604824736)), stylesSet.getStyles(3)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14189210175397, 126.92847604824736), LatLng.from(35.143050636977776, 126.92896060997579)), stylesSet.getStyles(2)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.143050636977776, 126.92896060997579), LatLng.from(35.143337077363654, 126.92940204403958)), stylesSet.getStyles(1)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.143337077363654, 126.92940204403958), LatLng.from(35.14347166598681, 126.93227150004542)), stylesSet.getStyles(0)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14347166598681, 126.93227150004542), LatLng.from(35.14340417411254, 126.9324690789298)), stylesSet.getStyles(1)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14340417411254, 126.9324690789298), LatLng.from(35.14349436678496, 126.9325705094070)), stylesSet.getStyles(2)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14349436678496, 126.9325705094070), LatLng.from(35.14273556030285, 126.93364104257898)), stylesSet.getStyles(3)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14273556030285, 126.93364104257898), LatLng.from(35.14305783460727,126.93370936508956)), stylesSet.getStyles(0)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14305783460727,126.93370936508956), LatLng.from(35.144254376951416,126.9336809608815)), stylesSet.getStyles(1)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.144254376951416,126.9336809608815), LatLng.from(35.14505894239145, 126.93386411804815)), stylesSet.getStyles(2)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.14505894239145, 126.93386411804815), LatLng.from(35.1451605262533, 126.93419599133837)), stylesSet.getStyles(3)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.1451605262533, 126.93419599133837), LatLng.from(35.145336297654694,126.93420682361685)), stylesSet.getStyles(0)),
                                RouteLineSegment.from(Arrays.asList(LatLng.from(35.145336297654694,126.93420682361685), LatLng.from(35.14539058429644,126.93458537483369)), stylesSet.getStyles(1)));

                        options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                        routeLine = layer.addRouteLine(options);
                    }

                    @Override
                    public LatLng getPosition() {
                        // 지도 시작 시 위치 좌표를 설정
                        return LatLng.from( 35.1441139,126.9331041);
                    }

                    @Override
                    public int getZoomLevel() {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return 18;
                    }

                    @Override
                    public MapViewInfo getMapViewInfo() {
                        // 지도 시작 시 App 및 MapType 설정
                        return MapViewInfo.from("openmap",MapType.NORMAL);
                    }

                    @Override
                    public String getViewName() {
                        // KakaoMap 의 고유한 이름을 설정
                        return "MyFirstMap";
                    }

                    @Override
                    public boolean isVisible() {
                        // 지도 시작 시 visible 여부를 설정
                        return true;
                    }

                    @Override
                    public String getTag() {
                        // KakaoMap 의 tag 을 설정
                        return "FirstMapTag";
                    }

                });
            }
        });
    }
}
