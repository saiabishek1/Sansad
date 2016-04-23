package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by utk994 on 23-Nov-15.
 */
public class GCMResponse {


        @Expose
        private int error;
        @Expose
        private String message;
        @Expose
        private ArrayList data;

        /**
         *
         * @return
         *     The error
         */
        public int getError() {
            return error;
        }

        /**
         *
         * @param error
         *     The error
         */
        public void setError(int error) {
            this.error = error;
        }

        /**
         *
         * @return
         *     The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message
         *     The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         *
         * @return
         *     The data
         */
        public ArrayList getData() {
            return data;
        }

        /**
         *
         * @param data
         *     The data
         */
        public void setData(ArrayList data) {
            this.data = data;
        }

    }

