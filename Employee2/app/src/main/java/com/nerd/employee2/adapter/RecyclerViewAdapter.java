package com.nerd.employee2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nerd.employee2.R;
import com.nerd.employee2.model.Employee;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Employee> employeeArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Employee> employeeList){
        this.context = context;
        this.employeeArrayList = employeeList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 1. 첫번째 파라미터인, parent 로 부터 뷰(화면 : 하나의 셀)를 생성한다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_row,parent,false); //inflate=만들라는 뜻
        // 2. 리턴에, 위에서 생성한 뷰를 뷰홀더에 담아서 리턴한다.
        return new ViewHolder(view);
        // 3. public 뒤에 부분을 클릭하면 ViewHolder 라고 써있는 부분이 2개있는데 2개다 02<>안 부분으로 바꿔줌(RecyclerViewAdapter.ViewHolder 로 함수 리턴값 변경).
    }

    // 03 - 3 과 동일.
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 04. 어레이리스트에 지정된 데이터를 화면과 연결 : bind
        // 1. 해당 포지션의 데이터 가져와서
        Employee employee = employeeArrayList.get(position);
        String name = employee.getEmployee_name();
        int age = employee.getEmployee_age();
        int salary = employee.getEmployee_salary();

        // 2. 뷰홀더에 있는 텍스트뷰에 문자열을 셋팅한다.(= 화면에 셋팅.)
        holder.txtName.setText(name);
        holder.txtAge.setText("나이 : "+age+"세");     // 문자열 + int = 문자열.
        holder.txtSalary.setText("연봉 : $"+salary);

    }

    // 05. 아이템갯수 리턴하는 메소드 구현.
    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }


    // 01. 뷰홀더 클래스 먼저 만들기 (employee 랑 매칭시킬 클래스)
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1.멤버변수 선언.
        public TextView txtName;
        public TextView txtAge;
        public TextView txtSalary;
        public ImageView img;

        // 01-0. alt + enter 치면 나옴
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 2. 생성자 안에서, 멤버변수 연결
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            img = itemView.findViewById(R.id.img);
            img.setImageResource(R.drawable.img);

        }
    }

}