package com.example.menutest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private boolean flag = false;
	private int num;
	TextView tView[] = new TextView[4];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int[] files=new int[]{R.id.file01,R.id.file02,R.id.file03,R.id.file04};//���ı���id��ɵ�����
		for(int i=0;i<tView.length;i++){//��ʼ������
			tView[i]=(TextView) findViewById(files[i]);//����id�ҵ���Ӧ���ı���
			registerForContextMenu(tView[i]);//Ϊ�ı���ע�������Ĳ˵�
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.start:
		case R.id.stop:
			invalidateOptionsMenu();//����ѡ��˵�
			break;
		case R.id.exit:
			finish();
			break;
		default:
			break;
		
		}
		Toast.makeText(MainActivity.this, item.getTitle()+"�������ˣ�", 1000).show();
		return true;
	}
	
	private void invalidateOptionsMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	    super.onPrepareOptionsMenu(menu);
		MenuItem start=menu.findItem(R.id.start);
		MenuItem stop=menu.findItem(R.id.stop);
		start.setEnabled(flag);
		stop.setEnabled(!flag);
		flag=!flag;
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){//�ж���Ҫע���������Ĳ˵��Ŀؼ�
		case R.id.file01:
			num=1;//numֵΪ1����Ҫ��Ϊ�����id����
		case R.id.file02:
			num=2;
			break;
		case R.id.file03:
			num=3;
			break;
		case R.id.file04:
			num=4;
			break;
		default:
			break;	
		}
		menu.setHeaderTitle("�ļ�����");//�����Ĳ˵��ı���
		menu.add(0,Menu.FIRST+num*10+1,0,"����");//��ӷ��Ͳ˵���
		SubMenu subMenu=menu.addSubMenu(0,Menu.FIRST+num*10+2,1,"�������ֵ���ɫ");//����Ӳ˵�
		subMenu.setHeaderTitle("The Second Level Menu");//�Ӳ˵��ı���
		subMenu.add(0,Menu.FIRST+num*100+21,0,"��ɫ");//�Ӳ˵��������
		subMenu.add(0,Menu.FIRST+num*100+22,0,"��ɫ");
		subMenu.add(0,Menu.FIRST+num*100+23,0,"��ɫ");
		menu.add(0,Menu.FIRST+num*10+3,2,"������");//��Ӳ˵���
		menu.add(0,Menu.FIRST+num*10+4,3,"ɾ��");
		super.onCreateContextMenu(menu, v, menuInfo);//���ø���ĸ÷���
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		String mesString="��ѡ����ǣ�";//��ʾ����ʾ��Ϣ
		int count=item.getItemId()-Menu.FIRST;
		num=count/10;//����num��ֵ
		if(num>10){
			num=num/10;//ȷ��numֵ������һ��
		}
		if(item.getItemId()==(Menu.FIRST+num*10+1)){//�Ƿ�ѡ���Ͳ˵���
			mesString+="����";//ƴ����Ϣ��ֵ
		}else if(item.getItemId()==(Menu.FIRST+num*10+2)){//�Ƿ�ѡ����ɫ�˵���
			mesString+="������ɫ���ý���";//ƴ����Ϣ��ֵ
		}else if(item.getItemId()==(Menu.FIRST+num*100+21)){//�Ƿ�ѡ���ɫ
			tView[num-1].setTextColor(Color.RED);//���ö�Ӧ�ı������ɫ
		}else if(item.getItemId()==(Menu.FIRST+num*100+22)){
			tView[num-1].setTextColor(Color.YELLOW);
		}else if(item.getItemId()==(Menu.FIRST+num*100+23)){
			tView[num-1].setTextColor(Color.GREEN);
		}else if(item.getItemId()==(Menu.FIRST+num*10+3)){
			final EditText inputname=new EditText(this);//����һ���ı��༭��
			//��������Ի���
			AlertDialog bDialog=new AlertDialog.Builder(MainActivity.this)
			.setIcon(android.R.drawable.btn_star).setTitle("������������").setView(inputname)//���öԻ���ͼ�꣬���⣬��ʾ�Ŀؼ�
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					tView[num-1].setText(inputname.getText().toString());
				}
			}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			}).create();
			bDialog.show();
			mesString+="�������ɹ�";
		}else if(item.getItemId()==(Menu.FIRST+num*10+4)){
			mesString+="ɾ��";
		}
		Toast.makeText(this, mesString, Toast.LENGTH_LONG).show();
		return true;
	}
	
}
