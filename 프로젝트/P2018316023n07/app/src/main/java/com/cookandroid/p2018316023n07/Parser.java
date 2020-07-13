package com.cookandroid.p2018316023n07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class Parser extends BaseAdapter {
    private ArrayList<SNSViewItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public SNSViewItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        //xml 인플레이트 해줌
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parser, parent, false);
        }

        TextView blog_title = (TextView) convertView.findViewById(R.id.blog_title);
        TextView blogger_name = (TextView) convertView.findViewById(R.id.blogger_name);
        TextView post_date = (TextView) convertView.findViewById(R.id.post_date);


        //데이터 저장
        SNSViewItem snsViewItem = getItem(position);

        //저장된 정보를 각각에 저장
        blog_title.setText(snsViewItem.getTitle());
        blogger_name.setText(snsViewItem.getBloggername());
        post_date.setText(snsViewItem.getPostdate());

        return convertView;
    }

    //네이버 블로그 검색 중, 제목, 블로거이름, 포스팅 일자, 포스트 링크 순서
    public void addItem(String title, String bloggername, String postdate, String link) {

        SNSViewItem mItem = new SNSViewItem();

        mItem.setTitle(title);
        mItem.setBloggername(bloggername);
        mItem.setPostdate(postdate);
        mItem.setBloglink(link);

        /* 데이터그릇 mItem에 담음 */
        mItems.add(mItem);
    }
    //블로그 데이터 게터 세터
    public class SNSViewItem {

        private String title;
        private String bloggername;
        private String postdate;
        private String bloglink;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBloggername() {
            return bloggername;
        }

        public void setBloggername(String bloggername) {
            this.bloggername = bloggername;
        }

        public String getPostdate() {
            return postdate;
        }

        public void setPostdate(String postdate) {
            this.postdate = postdate;
        }

        public String getBloglink() {
            return bloglink;
        }

        public void setBloglink(String bloglink) {
            this.bloglink = bloglink;
        }
    }
}