package com.example.appgame3;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SokobanActivity extends AppCompatActivity {
    //    biến model
    Model model = new Model();
    //    biến cho View
    Button btnBack;
    Button btnResetLevel;
    EditText inputLevel;
    Button btnSetLevel;
    ImageView[][] arrBoardImg;
    LinearLayout containerThanhDieuKhien;
    LinearLayout layoutMain;
    static final int ROW = 9;
    static final int COL = 9;
    //    biến cho phần xử lý sk
    int[] arrIDBtnControl = {R.id.btnUp, R.id.btnRight, R.id.btnDown, R.id.btnLeft};
    Button[] arrBtnControl;
    boolean isWaitingForNextLevel = false;
    boolean dungChungO = false;
    MediaPlayer mediaPlayerSoundRomantic;
    MediaPlayer mediaPlayerSoundFootstep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sokoban);

        mediaPlayerSoundRomantic = MediaPlayer.create(this, R.raw.romantic_music_meme_sound);
        mediaPlayerSoundFootstep = MediaPlayer.create(this, R.raw.pop_sound_effect);

        layoutMain = findViewById(R.id.layoutMain);
        containerThanhDieuKhien = findViewById(R.id.containerThanhDieuKhien);
//        containerThanhDieuKhien.setBackgroundResource(R.drawable.ttg_tang_hoa);
        btnBack = findViewById(R.id.buttonBack);
        btnResetLevel = findViewById(R.id.btnReset);
        inputLevel = findViewById(R.id.inputLevel);
        inputLevel.setText(model.level + "");
        btnSetLevel = findViewById(R.id.btnSetLevel);
        arrBtnControl = new Button[4];
        for (int i = 0; i < arrIDBtnControl.length; i++) {
            arrBtnControl[i] = findViewById(arrIDBtnControl[i]);
        }

        this.arrBoardImg = new ImageView[ROW][COL];

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                ImageView imageView = new ImageView(this);
                this.arrBoardImg[i][j] = imageView;
                imageView.setImageResource(R.drawable.crate_07); // Đặt hình ảnh từ tài nguyên drawable
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();

                // Lấy kích thước màn hình
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int screenWidth = displayMetrics.widthPixels;
                int screenHeight = displayMetrics.heightPixels;

                // Tính toán kích thước mỗi cell (dựa trên chiều rộng)
                int cellSizeWidthPx = screenWidth / COL;
                int cellSizeHeightPx = cellSizeWidthPx;

                params.width = cellSizeWidthPx;
                params.height = cellSizeHeightPx;

                params.rowSpec = GridLayout.spec(i);
                params.columnSpec = GridLayout.spec(j);
                imageView.setLayoutParams(params);
                gridLayout.addView(imageView);
            }
        }

        registerEvent();

        updateView(model.board);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sokoban), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        dungChungO = false;
        mediaPlayerSoundRomantic.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        effectWhenDungChungO();
    }

    public void updateView(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int intImg = Model.TREE;

                switch (board[i][j]) {
                    case Model.TREE:
                        intImg = R.drawable.ground_04;
                        break;
                    case Model.FLOOR:
                        intImg = R.drawable.ground_04;
                        break;
                    case Model.WALL:
                        intImg = R.drawable.block_06;
                        break;
                    case Model.BOX:
                        intImg = R.drawable.crate_07;
                        break;
                    case Model.BOMB:
//                        intImg = R.drawable.environment_11;
                        intImg = R.drawable.ttg_quy_tang_hoa;
                        break;
                    case Model.BOX_BOMB:
                        intImg = R.drawable.crate_09;
                        break;
                    case Model.PLAYER:
//                        intImg = R.drawable.player_face_dark;
                        intImg = R.drawable.co_lan_dung;
                        break;
                    case Model.PLAYER_BOMB:
//                        intImg = R.drawable.ret_74;
                        intImg = R.drawable.ttg_tang_hoa;
                        break;
                }

                arrBoardImg[i][j].setImageResource(intImg);

                if (board[i][j] == Model.FLOOR) {
                    arrBoardImg[i][j].setVisibility(View.INVISIBLE);
                } else {
                    arrBoardImg[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void registerEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerSoundRomantic.pause();
                finish();
            }
        });

        btnResetLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processSetLevel();
            }
        });

        btnSetLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processSetLevel();
            }
        });

        for (int i = 0; i < 4; i++) {
            arrBtnControl[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isWaitingForNextLevel) {
                        Toast.makeText(getApplicationContext(), "Đang chờ chuyển level!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int direction = findElementPosition(arrIDBtnControl, view.getId());
                    if (direction == -1)
                        return;

                    model.diChuyenNhanVat(direction);
                    updateView(model.board);

                    if (effectWhenDungChungO() == false) {
                        playFootstep();
                    }

                    if (model.checkCompleteLevel()) {
                        isWaitingForNextLevel = true;
                        System.out.println("Hoàn thành level " + model.level);
                        threadChuyenLevel();
                    }
                }
            });
        }

//      tạo cảm giác khi chạm vào layout bên ngoài input thì ẩn bàn phím đi
        layoutMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (inputLevel.isFocused()) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(inputLevel.getWindowToken(), 0);
                        return true;
                    }
                }
                return false;
            }
        });

        mediaPlayerSoundRomantic.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayerSoundRomantic.pause();
            }
        });

        mediaPlayerSoundFootstep.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayerSoundFootstep.pause();
            }
        });
    }

    private void playFootstep() {
        mediaPlayerSoundFootstep.seekTo(70);
        mediaPlayerSoundFootstep.start();
    }

    private boolean effectWhenDungChungO() {
        // Thêm vô hiệu ứng khi 2 nhân vật chung vị trí
        Cell currentPos = model.getPositionCharacter();
        int rowCur = currentPos.row;
        int colCur = currentPos.col;
        if (model.board[rowCur][colCur] == Model.PLAYER_BOMB) {
            containerThanhDieuKhien.setBackgroundResource(R.drawable.ttg_tang_hoa);
            if (dungChungO == false) {
                dungChungO = true;
                mediaPlayerSoundRomantic.seekTo(150);
                mediaPlayerSoundRomantic.start();
            }
            return true;
        } else {
            containerThanhDieuKhien.setBackgroundColor(Color.parseColor("#FFFFFF"));
            if (dungChungO == true) {
                dungChungO = false;
                mediaPlayerSoundRomantic.pause();
            }
            return false;
        }
    }

    private void processSetLevel() {
        if (isWaitingForNextLevel) {
            Toast.makeText(getApplicationContext(), "Không thể set level khi đang chuyển màn", Toast.LENGTH_SHORT).show();
            inputLevel.setText(model.level + "");
            return;
        }

        String strLevel = inputLevel.getText().toString();
        if (strLevel.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Không thể set level rỗng", Toast.LENGTH_SHORT).show();
            inputLevel.setText(model.level + "");
            return;
        }
        int level = Integer.parseInt(strLevel);
        if (level < 1 || level > 15) {
            Toast.makeText(getApplicationContext(), "Level chỉ từ 1 đến 15", Toast.LENGTH_SHORT).show();
            inputLevel.setText(model.level + "");
            return;
        }
        setLevel(level);
        effectWhenDungChungO();
    }

    public void threadChuyenLevel() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    Log.e("MyThread", "InterruptedException: " + e.getMessage());
                }

                // Cập nhật giao diện người dùng trên luồng chính
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setLevel(model.level + 1);
                        inputLevel.setText(model.level + "");
                        isWaitingForNextLevel = false;
                    }
                });
            }
        });
        thread.start();
    }

    public void setLevel(int level) {
        model.setLevel(level);
        updateView(model.board);
        Toast.makeText(getApplicationContext(), "Đang ở level " + level, Toast.LENGTH_SHORT).show();
    }

    public static int findElementPosition(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}