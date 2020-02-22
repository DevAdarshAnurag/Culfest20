package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.FAQ;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {
    private List<FAQ> faqs;
    private Context context;

    public FAQAdapter(List<FAQ> faqs, Context context) {
        this.faqs = faqs;
        this.context = context;
    }

    @NonNull
    @Override
    public FAQAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.faq_list_item, parent, false);
        return new FAQAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.MyViewHolder holder, int position) {
        FAQ faq = faqs.get(position);
        holder.faqQuestion.setText(faq.getQuestion());
        holder.faqAnswer.setText(faq.getAnswer());

        holder.ivFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.faqAnswer.getVisibility() == View.GONE) {
                    holder.ivFaq.setImageResource(R.drawable.ic_faq_less);
                    holder.faqAnswer.setVisibility(View.VISIBLE);
                } else {
                    holder.ivFaq.setImageResource(R.drawable.ic_faq_expand);
                    holder.faqAnswer.setVisibility(View.GONE);
                }
            }
        });

        holder.faqQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivFaq.setImageResource(R.drawable.ic_faq_less);
                holder.faqAnswer.setVisibility(View.VISIBLE);
            }
        });

    }

//    @Override
//    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        holder.ivFaq.setImageResource(R.drawable.ic_faq_expand);
//        holder.faqAnswer.setVisibility(View.GONE);
//    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        holder.ivFaq.setImageResource(R.drawable.ic_faq_expand);
        holder.faqAnswer.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFaq;
        TextView faqQuestion, faqAnswer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFaq = itemView.findViewById(R.id.faq_expand);
            faqQuestion = itemView.findViewById(R.id.question);
            faqAnswer = itemView.findViewById(R.id.answer);
        }
    }
}
