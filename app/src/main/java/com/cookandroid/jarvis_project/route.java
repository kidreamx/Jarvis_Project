package com.cookandroid.jarvis_project;
import static com.kakao.vectormap.shape.ShapeLayerPass.Route;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapReadyCallback;
import com.kakao.vectormap.MapType;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.MapViewInfo;
import com.kakao.vectormap.RoadViewRequest;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.camera.CameraUpdateFactory;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.mapwidget.InfoWindowLayer;
import com.kakao.vectormap.mapwidget.InfoWindowOptions;
import com.kakao.vectormap.mapwidget.MapWidgetOptions;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiLayout;
import com.kakao.vectormap.mapwidget.component.GuiText;
import com.kakao.vectormap.mapwidget.component.Orientation;
import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineLayer;
import com.kakao.vectormap.route.RouteLineOptions;
import com.kakao.vectormap.route.RouteLinePattern;
import com.kakao.vectormap.route.RouteLineSegment;
import com.kakao.vectormap.route.RouteLineStyle;
import com.kakao.vectormap.route.RouteLineStyles;
import com.kakao.vectormap.route.RouteLineStylesSet;

import java.util.Arrays;
import java.util.List;

public class route extends AppCompatActivity {
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
    Label label1;
    Label label2;
    CameraUpdate cameraUpdate;
    Button find;
    AutoCompleteTextView dropdown_start , dropdown_end;

    Button route_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.route);
        KakaoMapSdk.init(this, "e0036c32372bfece6a328fdecf90f17c");
        MapView mapView = findViewById(R.id.map_view);
        find = (Button)findViewById(R.id.find);
        dropdown_start = (AutoCompleteTextView)findViewById(R.id.dropdown_start);
        dropdown_end = (AutoCompleteTextView)findViewById(R.id.dropdown_end);
        route_back = (Button)findViewById(R.id.route_back);

        String[] departure = {"IT융합대학", "본관", "학생회관", "경상대및법대", "글로벌인문대학","미술대학","체대","공대1공학관", "공대2공학관", "사회과학대학", "자연과학대학","의대", "약대", "중도", "솔마루"};
        String[] destination = {"IT융합대학", "본관", "학생회관", "경상대및법대", "글로벌인문대학","미술대학","체대","공대1공학관", "공대2공학관", "사회과학대학", "자연과학대학","의대", "약대", "중도", "솔마루"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, departure);
        dropdown_start.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, destination);
        dropdown_end.setAdapter(adapter2);
        route_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        dropdown_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_start.showDropDown();
                dropdown_start.setDropDownHeight(500);
            }
        });
        dropdown_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_end.showDropDown();
                dropdown_end.setDropDownHeight(500);
            }
        });
        dropdown_start.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });
        dropdown_end.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

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
                find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String start = dropdown_start.getText().toString();
                        String end = dropdown_end.getText().toString();
                        if(start.equals("IT융합대학")){
                            if(end.equals("본관")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoMain(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("학생회관")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoStudent(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("경상대및법대")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoCob(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("글로벌인문대학")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoGlobal(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("미술대학")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoArt(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("체대")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoPhysical(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("공대1공학관")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoEngineering2(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("공대2공학관")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoEngineering2(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("사회과학대학")){

                            }
                            else if(end.equals("자연과학대학")){

                            }
                            else if(end.equals("의대")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoMedical(kakaoMap);
                                    }

                                });

                            }
                            else if(end.equals("약대")){

                            }
                            else if(end.equals("중도")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        ITtoLibrary(kakaoMap);
                                    }

                                });

                            }

                        }
                        else if(start.equals("본관")){
                                if(end.equals("IT융합대학")){
                                    mapView.start(new MapLifeCycleCallback() {
                                        @Override
                                        public void onMapDestroy() {

                                        }

                                        @Override
                                        public void onMapError(Exception e) {

                                        }
                                    }, new KakaoMapReadyCallback() {
                                        @Override
                                        public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                            ITtoMain(kakaoMap);
                                        }

                                    });

                                }
                                else if(end.equals("체대")){
                                    mapView.start(new MapLifeCycleCallback() {
                                        @Override
                                        public void onMapDestroy() {

                                        }

                                        @Override
                                        public void onMapError(Exception e) {

                                        }
                                    }, new KakaoMapReadyCallback() {
                                        @Override
                                        public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                            MaintoPhysical(kakaoMap);
                                        }

                                    });
                                }
                                else if(end.equals("글로벌")){
                                    mapView.start(new MapLifeCycleCallback() {
                                        @Override
                                        public void onMapDestroy() {

                                        }

                                        @Override
                                        public void onMapError(Exception e) {

                                        }
                                    }, new KakaoMapReadyCallback() {
                                        @Override
                                        public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                            MaintoGlobal(kakaoMap);
                                        }

                                    });
                                }
                                else if(end.equals("약대")){
                                    mapView.start(new MapLifeCycleCallback() {
                                        @Override
                                        public void onMapDestroy() {

                                        }

                                        @Override
                                        public void onMapError(Exception e) {

                                        }
                                    }, new KakaoMapReadyCallback() {
                                        @Override
                                        public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                            MaintoPharmacy(kakaoMap);
                                        }

                                    });
                                }

                        }
                        else if(start.equals("학생회관")){

                        }
                        else if(start.equals("경상대및법대")){

                        }
                        else if(start.equals("글로벌인문대학")){

                        }
                        else if(start.equals("미술대학")){

                        }
                        else if(start.equals("체대")){

                        }
                        else if(start.equals("공대1공학관")){
                             if(end.equals("본관")){
                                mapView.start(new MapLifeCycleCallback() {
                                    @Override
                                    public void onMapDestroy() {

                                    }

                                    @Override
                                    public void onMapError(Exception e) {

                                    }
                                }, new KakaoMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                        Engineering1toMain(kakaoMap);
                                    }

                                });
                            }
                             else if(end.equals("미술대학")){
                                 mapView.start(new MapLifeCycleCallback() {
                                     @Override
                                     public void onMapDestroy() {

                                     }

                                     @Override
                                     public void onMapError(Exception e) {

                                     }
                                 }, new KakaoMapReadyCallback() {
                                     @Override
                                     public void onMapReady(@NonNull KakaoMap kakaoMap) {
                                         Engineering1toArt(kakaoMap);
                                     }

                                 });
                             }
                        }
                        else if(start.equals("공대2공학관")){

                        }
                        else if(start.equals("사회과학대학")){

                        }
                        else if(start.equals("자연과학대학")){

                        }
                        else if(start.equals("의대")){

                        }
                        else if(start.equals("약대")){

                        }
                        else if(start.equals("중도")){

                        }



                    }
                });
            }
            public void Engineering1toPhysical(KakaoMap kakaoMap){

            }
            public void Engineering1toArt(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.13919988, 126.93349852),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("공대1호관");
                text.setTextSize(30);
                text2 = new GuiText("미대");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1418632, 126.9255215)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1440294, 126.9304329)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1418632, 126.9255215)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1440294, 126.9304329)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418632, 126.9255215),LatLng.from(35.1421719, 126.9255102)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1421719, 126.9255102),LatLng.from(35.1421880, 126.9259574)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1421880, 126.9259574) ,LatLng.from(35.1421633, 126.9260561)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1421633, 126.9260561), LatLng.from(35.1421501, 126.9266323)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1421501, 126.9266323), LatLng.from(35.1418889, 126.9269727)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418889, 126.9269727), LatLng.from(35.1417019, 126.9270469)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1417019, 126.9270469), LatLng.from(35.1419071, 126.9273210)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1419071, 126.9273210), LatLng.from(35.1419727, 126.9276776)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1419727, 126.9276776), LatLng.from(35.1418468, 126.9280865)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418468, 126.9280865), LatLng.from(35.1420203, 126.9281631)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1420203, 126.9281631), LatLng.from(35.1420069, 126.9283250)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1420069, 126.9283250), LatLng.from(35.1420588, 126.9284210)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1420588, 126.9284210), LatLng.from(35.1430460, 126.9288399)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1430460, 126.9288399), LatLng.from(35.1431002, 126.9289852)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1431002, 126.9289852), LatLng.from(35.1432355, 126.9291607)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1432355, 126.9291607), LatLng.from(35.1433303, 126.9293965)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1433303, 126.9293965), LatLng.from(35.1434660, 126.9303621)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1434660, 126.9303621), LatLng.from(35.1436328, 126.9303400)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1436328, 126.9303400), LatLng.from(35.1440072, 126.9309624)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1440072, 126.9309624), LatLng.from(35.1440294, 126.9304329)), stylesSet.getStyles(1)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void Engineering1toMain(KakaoMap kakaoMap){
                // 카메라 위치 수정해야함
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.13919988, 126.93349852),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("공대1호관");
                text.setTextSize(30);
                text2 = new GuiText("본관");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1418632, 126.9255215)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1427474, 126.9347054)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1418632, 126.9255215)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1427474, 126.9347054)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418632, 126.9255215),LatLng.from(35.14218100, 126.9255075)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14218100, 126.9255075),LatLng.from(35.1421700, 126.92606167)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1421700, 126.92606167) ,LatLng.from(35.1421546, 126.9266130)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1421546, 126.9266130), LatLng.from(35.1419925, 126.9268903)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1419925, 126.9268903), LatLng.from(35.1418529, 126.9270029)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418529, 126.9270029), LatLng.from(35.1417087, 126.927044)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1417087, 126.927044), LatLng.from(35.1419727, 126.9276694)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1419727, 126.9276694), LatLng.from(35.1418108, 126.9282374)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418108, 126.9282374), LatLng.from(35.1416982, 126.9283445)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1416982, 126.9283445), LatLng.from(35.1417275, 126.9284597)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1417275, 126.9284597), LatLng.from(35.1418470, 126.9284321)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418470, 126.9284321), LatLng.from(35.1432265, 126.9291278)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1432265, 126.9291278), LatLng.from(35.1433483, 126.9294184)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1433483, 126.9294184), LatLng.from(35.1435023, 126.9307735)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1435023, 126.9307735), LatLng.from(35.1434716, 126.9322440)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1434716, 126.9322440), LatLng.from(35.1433951, 126.9324965)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1433951, 126.9324965), LatLng.from(35.1424941, 126.9331913)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1424941, 126.9331913), LatLng.from(35.1427474, 126.9347054)), stylesSet.getStyles(0)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void MaintoPharmacy(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.13919988, 126.93349852),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("본관");
                text.setTextSize(30);
                text2 = new GuiText("약대");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1427474, 126.9347054)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1417332, 126.9266957)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1427474, 126.9347054)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1417332, 126.9266957)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1427474, 126.9347054),LatLng.from(35.142862143, 126.9343624)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.142862143, 126.9343624),LatLng.from(35.1415147, 126.9346817)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1415147, 126.9346817) ,LatLng.from(35.1413747, 126.9340508)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1413747, 126.9340508), LatLng.from(35.1433884, 126.9325074)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1433884, 126.9325074), LatLng.from(35.1434603, 126.9322276)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1434603, 126.9322276), LatLng.from(35.1435000, 126.93068031)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1435000, 126.93068031), LatLng.from(35.1433054, 126.9292841)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1433054, 126.9292841), LatLng.from(35.1431137, 126.9290044)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1431137, 126.9290044), LatLng.from(35.1418582, 126.9284541)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418582, 126.9284541), LatLng.from(35.14188299, 126.9283169)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14188299, 126.9283169), LatLng.from(35.14180408, 126.9282429)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14180408, 126.9282429), LatLng.from(35.1419705, 126.92768590)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1419705, 126.92768590), LatLng.from(35.1419342, 126.9273869)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1419342, 126.9273869), LatLng.from(35.14170421, 126.9270387)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14170421, 126.9270387), LatLng.from(35.1418506, 126.9269974)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1418506, 126.9269974), LatLng.from(35.1417332, 126.9266957)), stylesSet.getStyles(0)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void MaintoPhysical(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.13919988, 126.93349852),16);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("본관");
                text.setTextSize(30);
                text2 = new GuiText("체대");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1427474, 126.9347054)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1408459, 126.9275195)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1427474, 126.9347054)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1408459, 126.9275195)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1427474, 126.9347054),LatLng.from(35.14284862, 126.9343597)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14284862, 126.9343597),LatLng.from(35.1409807, 126.93479464)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1409807, 126.93479464) ,LatLng.from(35.14087477, 126.93460270)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14087477, 126.93460270), LatLng.from(35.1409355, 126.9344408)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1409355, 126.9344408), LatLng.from(35.1407799, 126.9341995)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1407799, 126.9341995), LatLng.from(35.1399979, 126.9342193)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1399979, 126.9342193), LatLng.from(35.1399886, 126.93368716)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1399886, 126.93368716), LatLng.from(35.13940955, 126.9336821)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.13940955, 126.9336821), LatLng.from(35.1393554, 126.93364378)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1393554, 126.93364378), LatLng.from(35.13919546, 126.9336494)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.13919546, 126.9336494), LatLng.from(35.13919988, 126.93349852)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.13919988, 126.93349852), LatLng.from(35.13897196, 126.93289245)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.13897196, 126.93289245), LatLng.from(35.13917211, 126.9321735)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.13917211, 126.9321735), LatLng.from(35.13902708, 126.9307334)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.13902708, 126.9307334), LatLng.from(35.1399283, 126.93048857)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1399283, 126.93048857), LatLng.from(35.14041650, 126.92913023)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14041650, 126.92913023), LatLng.from(35.14044568, 126.9289409)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14044568, 126.9289409), LatLng.from(35.1408394, 126.9280078)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1408394, 126.9280078), LatLng.from(35.140672680, 126.9279202)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140672680, 126.9279202), LatLng.from(35.14034589, 126.92784919)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14034589, 126.92784919), LatLng.from(35.1408459, 126.9275195)), stylesSet.getStyles(1)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void MaintoGlobal(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.14086613434125,126.93121474856689),16);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("본관");
                text.setTextSize(30);
                text2 = new GuiText("글로벌");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1427474, 126.9347054)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1415960, 126.9349889)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1427474, 126.9347054)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1415960, 126.9349889)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1427474, 126.9347054),LatLng.from(35.14285087, 126.93435696)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14285087, 126.93435696),LatLng.from(35.14171530, 126.93462123)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14171530, 126.93462123) ,LatLng.from(35.1415960, 126.9349889)), stylesSet.getStyles(2)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void MaintoStudent(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from( 35.1431569288083,126.93360778018854),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("본관");
                text.setTextSize(30);
                text2 = new GuiText("학생회관");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1427474, 126.9347054)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1442472, 126.9330609)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1427474, 126.9347054)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1442472, 126.9330609)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1427474, 126.9347054),LatLng.from(35.14285087, 126.93435696)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14285087, 126.93435696),LatLng.from(35.14406530, 126.93408165)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14406530, 126.93408165) ,LatLng.from(35.144062831, 126.9336701)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.144062831, 126.9336701), LatLng.from(35.1442472, 126.93306095)), stylesSet.getStyles(3)));

                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }

            public void ITtoMedical(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from( 35.140871154694516,126.93212551650666),16);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("의과대학");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1405761,126.9295721)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1405761,126.9295721)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.140457429943055,126.93375536575506)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140457429943055,126.93375536575506),LatLng.from(35.1405361081626, 126.93340690565131)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1405361081626, 126.93340690565131) ,LatLng.from(35.14049511166884,126.93261961852396)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14049511166884,126.93261961852396), LatLng.from(35.140902945879986 , 126.93256167334255)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140902945879986 , 126.93256167334255), LatLng.from(35.14086204107157, 126.93194721026416)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14086204107157, 126.93194721026416), LatLng.from(35.14087326956168, 126.93187861867584)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14087326956168, 126.93187861867584), LatLng.from(35.14097241011104 , 126.93186207632174)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14097241011104 , 126.93186207632174), LatLng.from(35.14073689601282, 126.92982949830028)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14073689601282, 126.92982949830028), LatLng.from(35.1407377,126.9296577)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1407377,126.9296577), LatLng.from(35.1405761,126.9295721)), stylesSet.getStyles(3)));

                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }

            public void ITtoLibrary(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from( 35.14086613434125,126.93121474856689),16);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("중앙도서관");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.14181334723694, 126.93260755892156)); // 중도
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.14101046660196,126.92754409940731)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.140457429943055,126.93375536575506)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140457429943055,126.93375536575506),LatLng.from(35.1405361081626, 126.93340690565131)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1405361081626, 126.93340690565131) ,LatLng.from(35.14049511166884,126.93261961852396)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14049511166884,126.93261961852396), LatLng.from(35.140902945879986 , 126.93256167334255)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140902945879986 , 126.93256167334255), LatLng.from(35.14086204107157, 126.93194721026416)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14086204107157, 126.93194721026416), LatLng.from(35.14181065804816, 126.93182845792305)), stylesSet.getStyles(1)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void ITtoPhysical(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.14086613434125,126.93121474856689),16);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("체대");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.14101046660196,126.92754409940731)); // 체대
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.14101046660196,126.92754409940731)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.140457429943055,126.93375536575506)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140457429943055,126.93375536575506),LatLng.from(35.1405361081626, 126.93340690565131)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1405361081626, 126.93340690565131) ,LatLng.from(35.14049511166884,126.93261961852396)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14049511166884,126.93261961852396), LatLng.from(35.140902945879986 , 126.93256167334255)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140902945879986 , 126.93256167334255), LatLng.from(35.14086204107157, 126.93194721026416)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14086204107157, 126.93194721026416), LatLng.from(35.14087326956168, 126.93187861867584)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14087326956168, 126.93187861867584), LatLng.from(35.14097241011104 , 126.93186207632174)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14097241011104 , 126.93186207632174), LatLng.from(35.14073689601282, 126.92982949830028)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14073689601282, 126.92982949830028), LatLng.from(35.14093718287345, 126.92937119668446)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14093718287345, 126.92937119668446), LatLng.from(35.14143272988759, 126.92902511177027)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14143272988759, 126.92902511177027), LatLng.from(35.1416847788579,126.92845976970342)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1416847788579,126.92845976970342), LatLng.from(35.14106491253418, 126.92815580618509)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14106491253418, 126.92815580618509), LatLng.from(35.14120202236998,126.92757136266957)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.14120202236998,126.92757136266957), LatLng.from(35.14101046660196,126.927544099407317)), stylesSet.getStyles(1)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }

            public void ITtoArt(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from( 35.141858415323135,126.93260752177505),16);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("미술대학");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1438275,126.9304451)); // 미술
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1438275,126.9304451)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.1411657,126.934176)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1411657,126.934176),LatLng.from(35.1433745,126.9325149)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1433745,126.9325149) ,LatLng.from(35.1434387,126.9309252)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1434387,126.9309252), LatLng.from(35.1436148,126.9309774)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1436148,126.9309774), LatLng.from(35.1438275,126.9304451)), stylesSet.getStyles(2)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void ITtoGlobal(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.13939434969446,126.93472733400199),17);
                kakaoMap.moveCamera(cameraUpdate);

                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("글로벌");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1379237,126.934058)); // 글로벌
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1379237,126.934058)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.140674,126.9342208)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.140674,126.9342208),LatLng.from(35.1401564,126.9349949)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401564,126.9349949) ,LatLng.from(35.1393048,126.9350561)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1393048,126.9350561), LatLng.from(35.1390796,126.9345142)), stylesSet.getStyles(3)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1390796,126.9345142), LatLng.from(35.1384774,126.9340656)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1384774,126.9340656), LatLng.from(35.1379237,126.934058)), stylesSet.getStyles(1)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void ITtoStudent(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.1423107,126.93362267),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("학생회관");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.1438687,126.9330433)); // 공대2호관쪽
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1438687,126.9330433)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.1411269,126.9342029)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1411269,126.9342029),LatLng.from(35.1432903,126.9325651)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1432903,126.9325651) ,LatLng.from(35.1434341,126.9330874)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1434341,126.9330874), LatLng.from(35.1438687,126.9330433)), stylesSet.getStyles(3)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }

            public void ITtoEngineering2(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.139436649442324 , 126.93377813865337),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                mainLayout2 = new GuiLayout(Orientation.Horizontal);
                mainLayout2.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                mainLayout2.setBackground(bgImage);
                text = new GuiText("It융합대학");
                text.setTextSize(30);
                text2 = new GuiText("공대 2호관");
                text2.setTextSize(30);
                mainLayout.addView(text);
                mainLayout2.addView(text2);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400912,126.9344539)); // it융합대학쪽
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                options2 = InfoWindowOptions.from(LatLng.from(35.13881693447228 , 126.93372651932226)); // 공대2호관쪽
                options2.setZOrder(-1);
                options2.setBody(mainLayout2);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options2);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1388284,126.9333356)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(16,Color.GREEN));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.1393956,126.9337007)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1393956,126.9337007),LatLng.from(35.1391479,126.9336241)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1391479,126.9336241) ,LatLng.from(35.1390567,126.9333455)), stylesSet.getStyles(2)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1390567,126.9333455), LatLng.from(35.1388284,126.9333356)), stylesSet.getStyles(3)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void ITtoCob(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.140349,126.9348703),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                text = new GuiText("IT융합대학 5층 이용");
                text.setTextSize(30);
                mainLayout.addView(text);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400821,126.9348375));
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1393933,126.9355537)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(12,Color.BLUE));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.1406215,126.9341986)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1406215,126.9341986),LatLng.from(35.1400489,126.9353521)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1400489,126.9353521) ,LatLng.from(35.1393933,126.9355537)), stylesSet.getStyles(2)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }
            public void ITtoMain(KakaoMap kakaoMap){
                cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.1416018,126.9341743),17);
                kakaoMap.moveCamera(cameraUpdate);
                mainLayout = new GuiLayout(Orientation.Horizontal);
                mainLayout.setPadding(20, 20, 20, 18);
                bgImage = new GuiImage(R.drawable.window_body, true);
                bgImage.setFixedArea(7, 7, 7, 7); // 말풍선 이미지 각 모서리의 둥근 부분만큼(7px)은 늘어나지 않도록 고정.
                mainLayout.setBackground(bgImage);
                text = new GuiText("IT융합대학 5층 이용");
                text.setTextSize(30);
                mainLayout.addView(text);
                options1 = InfoWindowOptions.from(LatLng.from(35.1400821,126.9348375));
                options1.setZOrder(2);
                options1.setBody(mainLayout);
                kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options1);

                style1 = LabelStyles.from("myStyleId1", LabelStyle.from(R.drawable.redmarker).setZoomLevel(15));
                style1 = kakaoMap.getLabelManager().addLabelStyles(style1);
                label1 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker1",LatLng.from(35.1401045,126.9342291)).setStyles(style1));

                style2 = LabelStyles.from("myStyleId2", LabelStyle.from(R.drawable.bluemarker).setZoomLevel(15));
                style2 = kakaoMap.getLabelManager().addLabelStyles(style2);
                label2 = kakaoMap.getLabelManager().getLayer().addLabel(LabelOptions.from("marker2",LatLng.from(35.1427127,126.9345645)).setStyles(style2));

                layer = kakaoMap.getRouteLineManager().getLayer();
                styles1 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLACK));
                styles2 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE).setZoomLevel(10),
                        RouteLineStyle.from(20, Color.GREEN, 1, Color.WHITE).setZoomLevel(15));
                styles3 = RouteLineStyles.from(RouteLineStyle.from(16, Color.BLUE)
                );
                styles4 = RouteLineStyles.from(RouteLineStyle.from(12,Color.BLUE));

                stylesSet = RouteLineStylesSet.from(styles1, styles2, styles3, styles4);
                segments = Arrays.asList(RouteLineSegment.from(Arrays.asList(LatLng.from(35.1401045,126.9342291),LatLng.from(35.1406215,126.9341986)), stylesSet.getStyles(0)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1406215,126.9341986),LatLng.from(35.1410695,126.9350175)), stylesSet.getStyles(1)),
                        RouteLineSegment.from(Arrays.asList(LatLng.from(35.1410695,126.9350175) ,LatLng.from(35.1427127,126.9345645)), stylesSet.getStyles(2)));
                options = RouteLineOptions.from(segments).setStylesSet(stylesSet);
                routeLine = layer.addRouteLine(options);
            }

            @Override
            public LatLng getPosition() {
                // 지도 시작 시 위치 좌표를 설정
                return LatLng.from(35.1416018,126.9341743);
            }

            @Override
            public int getZoomLevel() {
                // 지도 시작 시 확대/축소 줌 레벨 설정
                return 17;
            }

            @Override
            public MapViewInfo getMapViewInfo() {
                // 지도 시작 시 App 및 MapType 설정
                return MapViewInfo.from("openmap", MapType.NORMAL);
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
}
