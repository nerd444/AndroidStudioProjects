package com.nerd.contactmanager.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nerd.contactmanager.MainActivity;
import com.nerd.contactmanager.R;
import com.nerd.contactmanager.data.DatabaseHandler;
import com.nerd.contactmanager.model.Contact;

import java.util.ArrayList;

// 마지막으로, Adapter 에, 우리가 만든 뷰홀더를 연결합니다.
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // 멤버변수 셋팅
    Context context;
    ArrayList<Contact> contactList;

    // 1. 생성자 만들기
    public RecyclerViewAdapter(Context context, ArrayList<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 첫번째 파라미터인, parent 로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        // 리턴에, 위에서 생성한 뷰를, 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
    }

    // 리스트에 있는 데이터(주소록데이터)를, 화면에 있는 뷰(텍스트뷰, 이미지뷰...)에 표시하는 메소드
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 1. 해당 포지션의 데이터 가져와서
        Contact contact = contactList.get(position);
        String name = contact.getName();
        String phone = contact.getPhone_number();
        // 2. 뷰홀더에 있는 텍스트뷰에 문자열을 셋팅한다.
        holder.txtName.setText(name);       // 앞에 holder 부분 => ((ViewHolder)holder) 이렇게 둬도 됌
        holder.txtPhone.setText(phone);
    }

    // 리스트에 있는 데이터의 갯수를 리턴해줘야 한다.
    @Override
    public int getItemCount() {
        return contactList.size();
    }


    // 하나의 셀 xml 화면에 있는 구성요소(텍스트 뷰, 이미지뷰 ...)를 여기서 연결한다.
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1. 멤버변수
        public TextView txtName;
        public TextView txtPhone;
        public ImageView imgDelete;
        // 카드뷰 클릭하면 화면 넘어갈 수 있도록, 멤버 변수 셋팅
        public CardView cardView;

        // 카드뷰 클릭 시 다이얼로그
        public EditText editName;
        public EditText editPhone;
        public Button btnSave;
        public Button btnCancle;

        public int id;
        public AlertDialog dialog;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            // 2. 생성자 안에서, 멤버변수 연결
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);
            // 카드뷰의 클릭이벤트 처리
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    View alertView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.update_dialog,null);

                    editName = alertView.findViewById(R.id.editName);
                    editPhone = alertView.findViewById(R.id.editPhone);
                    btnSave = alertView.findViewById(R.id.btnSave);
                    btnCancle = alertView.findViewById(R.id.btnCancle);

                    int index = getAdapterPosition();
                    Contact contact = contactList.get(index);
                    id = contact.getId();
                    String name = contact.getName();
                    String phone = contact.getPhone_number();

                    // 어댑터에서, 유저가 클릭한 경우, 에디트 텍스트에 보여준다.
                    editName.setText(name);
                    editPhone.setText(phone);

                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 데이터베이스에 업데이트.
                            // 유저가 변경했을 수 있는 이름과 폰번을, 에디트 텍스트에서 가져온다.
                            String name = editName.getText().toString().trim();
                            String phone = editPhone.getText().toString().trim();

                            // 디비핸들러 클래스를 통해서 업데이트
                            DatabaseHandler db = new DatabaseHandler(context);
                            Contact contact = new Contact(id, name, phone);
                            db.updateContact(contact);
                            // 토스트를 보여준다.
                            Toast.makeText(context, "수정되었습니다.",Toast.LENGTH_SHORT).show();
                            // 이 액티비티 종료.
//                            finish();

                            dialog.cancel();
                        }
                    });

                    btnCancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    alert.setView(alertView);
                    alert.setCancelable(false);

                    dialog = alert.create();
                    dialog.show();


//                    Intent i = new Intent(context, MainActivity.class);
//                    // 유저가 클릭한 셀의 인덱스를 가져온다. 이 인덱스만 알면, 어레이리스트에서 데이터 꺼내올 수 있다.
//                    int index = getAdapterPosition();
//                    Contact contact = contactList.get(index);
//                    int id = contact.getId();
//                    String name = contact.getName();
//                    String phone = contact.getPhone_number();
//                    // 업데이트 액티비티로 데이터 넘겨줄 수 있다.
//                    i.putExtra("id",id);
//                    i.putExtra("name",name);
//                    i.putExtra("phone",phone);

                    // 새로운 화면을 띄우는 startActivity 함수는, 액티비티 클래스의 메소드 이므로,
                    // context.startActivity 해야함. 왜냐면, context == MainActivity.this
//                    context.startActivity(i);

                    // 수정 액티비티로 넘어가는 코드 작성.
//                    Toast.makeText(context, "이 셀은 " + getAdapterPosition() + "번째 셀입니다.", Toast.LENGTH_SHORT).show();
                    // getAdapterPosition() 함수는, 현재 내가 클릭한 부분이, 몇번째 셀인지 알려준다.
                }
            });

            // X 버튼 처리.
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder finishAlert = new AlertDialog.Builder(context);
                    finishAlert.setTitle("주소록 삭제");
                    finishAlert.setMessage("정말 삭제하시겠습니까?");
                    finishAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 데이터베이스에서 삭제.
                            int index = getAdapterPosition();
                            Contact contact = contactList.get(index);
                            DatabaseHandler db = new DatabaseHandler(context);
                            db.deleteContact(contact);
                            String name = contact.getName();
                            Toast.makeText(context,name+"님이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                            // 데이터셋이 바뀌었다는 것을 알려주는 메소드 실행.
                            // 1번째 방법
                            contactList = db.getAllContacts();
                            notifyDataSetChanged();
                            // 2번째 방법
                            ((MainActivity)context).refresh();      // Main 에 refresh 함수 만든거 이용해서 지우는 법
//                            notifyItemChanged(index);       // 이거쓰면 인덱스가 밀려서 안됌.
                        }
                    });
                    finishAlert.setNegativeButton("No", null);
                    finishAlert.setCancelable(false);
                    finishAlert.show();
                }
            });

        }
    }

}
