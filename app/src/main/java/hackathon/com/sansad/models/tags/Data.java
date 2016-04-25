package hackathon.com.sansad.models.tags;

/**
 * Created by utk994 on 24-Apr-16.
 */


    public class Data
    {
        private String id;

        private String mps;

        private String tag;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getMps ()
        {
            return mps;
        }

        public void setMps (String mps)
        {
            this.mps = mps;
        }

        public String getTag ()
        {
            return tag;
        }

        public void setTag (String tag)
        {
            this.tag = tag;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", mps = "+mps+", tag = "+tag+"]";
        }
    }


