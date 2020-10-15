package com.nerd.addmemo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nerd.addmemo.R;
import com.nerd.addmemo.UpdateActivity;
import com.nerd.addmemo.data.DatabaseHandler;
import com.nerd.addmemo.model.Memo;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // 멤버변수 셋팅
    Context context;
    ArrayList<Memo> memoArrayList;

    // 1. 생성자 만들기
    public RecyclerViewAdapter(Context context, ArrayList<Memo> memoArrayList){
        this.context = context;
        this.memoArrayList = memoArrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent 로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_row,parent,false); //inflate=만들라는 뜻
        //리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }

    //리스트에 있는 데이터(주소록데이터)를, 화면에 있는 뷰(텍스트뷰, 이미지뷰...)에 표시하는 메소드
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 1. 해당 포지션의 데이터 가져와서
        Memo memo = memoArrayList.get(position);
        String title = memo.getTitle();
        String content = memo.getMemo();
        // 2. 뷰홀더에 있는 텍스트뷰에 문자열을 셋팅한다.
        holder.txtTitle.setText(title);
        holder.txtMemo.setText(content);

    }

    //리스트에 있는 데이터의 객수를 리턴해줘야 한다.
    @Override
    public int getItemCount() {
        return memoArrayList.size();
    }


    // 하나의 셀 (xml) 화면에 있는 구성요소(텍스트뷰, 이미지뷰 ...)를 여기서 연결한다.
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1. 멤버변수
        public TextView txtTitle;
        public TextView txtMemo;
        public ImageView imgDelete;
        // 카드뷰 클릭하면 화면 넘어갈 수 있도록, 멤버 변수 셋팅
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 2. 생성자 안에서, 멤버변수 연결
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtMemo = itemView.findViewById(R.id.txtMemo);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);
            // 카드뷰의 클릭이벤트 처리
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //수정 엑티비티로 넘어가는 코드 작성

                    Intent i = new Intent(context, UpdateActivity.class);
                    int index = getAdapterPosition();
                    Memo getMemo = memoArrayList.get(index);
                    int id = getMemo.getId();
                    String title = getMemo.getTitle();
                    String memo = getMemo.getMemo();
                    i.putExtra("id",id);
                    i.putExtra("title",title);
                    i.putExtra("memo",memo);
                    context.startActivity(i);

                }

            });
            //X버튼 처리
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context);
                    deleteAlert.setTitle("연락처 삭제");
                    deleteAlert.setMessage("정말 삭제하시겠습니까?");
                    deleteAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int index = getAdapterPosition();
                            Memo memo = memoArrayList.get(index);
                            DatabaseHandler db = new DatabaseHandler(context);
                            db.deleteMemo(memo);
                            // 데이터셋을 다시 가져오는 메소드
                            memoArrayList = db.getAllMemos();
                            // 데이터셋이 바꼈다는것을 알려주는 메소드실행.
                            notifyDataSetChanged();
                    //notifyItemchanged(index)라는 함수도 있다.
                        }
                    });
                    deleteAlert.setNegativeButton("NO", null);
                    deleteAlert.setCancelable(false);
                    deleteAlert.show();
                    return;

                }
            });

        }
    }

}
