package hackathon.com.sansad;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.annotation.IdRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.quinny898.library.persistentsearch.SearchBox;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hackathon.com.sansad.models.mp.MpData;
import hackathon.com.sansad.models.mp.MpResponse;
import hackathon.com.sansad.models.tags.Data;
import hackathon.com.sansad.models.tags.TagsModel;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class new_list_display extends Activity {

    FloatingActionButton debates;
    FloatingActionButton bills;
    FloatingActionButton question;
    FloatingActionButton z2a;
    FloatingActionButton a2z;
    FloatingActionButton bottom;
    FloatingActionButton top;
    FloatingActionButton attend;

    ArrayList<MpData> mpsList;
    ImageLoader imageLoader;

    DynamicListView list, list2;
    CustomAdapter adapter;
    SwingLeftInAnimationAdapter animationAdapter;


    Button names, pin;
    EditText search;

    FloatingActionMenu menu;
    Toolbar toolbar;

    ArrayList<Data> tags;

    BottomNavigation bottomNavigation;
    ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list_display);

        toolbar = (Toolbar) findViewById(R.id.toolbar12);
        menu = (FloatingActionMenu) findViewById(R.id.menu);

        tags = new ArrayList<>();

        list = (DynamicListView) findViewById(R.id.list);
        list.setDivider(null);
        list.setDividerHeight(0);
        list.setScrollingCacheEnabled(true);

        list2 = (DynamicListView) findViewById(R.id.list2);
        list2.setDivider(null);
        list2.setDividerHeight(0);
        list2.setScrollingCacheEnabled(true);



        names = (Button) findViewById(R.id.name_butt);
        pin = (Button) findViewById(R.id.pincode);
        attend = (FloatingActionButton) findViewById(R.id.menu_item8);
        debates = (FloatingActionButton) findViewById(R.id.menu_item7);
        bills = (FloatingActionButton) findViewById(R.id.menu_item6);
        question = (FloatingActionButton) findViewById(R.id.menu_item5);
        z2a = (FloatingActionButton) findViewById(R.id.menu_item4);
        a2z = (FloatingActionButton) findViewById(R.id.menu_item3);
        bottom = (FloatingActionButton) findViewById(R.id.menu_item2);
        top = (FloatingActionButton) findViewById(R.id.menu_item);

        search = (EditText) findViewById(R.id.search_toolbar);

        bottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);


        SQLiteDBHelper helper = new SQLiteDBHelper(new_list_display.this);
        String session = helper.getUserDetails().getSession().getSessionid();
        String token = helper.getUserDetails().getSession().getToken();

        apiClient.getAPI().getMp(session, token, new Callback<MpResponse>() {
            @Override
            public void success(MpResponse mpResponse, Response response) {

                mpsList = new ArrayList<MpData>();
                name = new ArrayList<String>();

                mpsList.addAll(mpResponse.getResponse().getData());


                Collections.sort(mpsList, new CustomComparator());
                Collections.reverse(mpsList);

                attend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return Integer.parseInt(lhs.getAttendance_percentage()) - Integer.parseInt(rhs.getAttendance_percentage());
                            }
                        });
                        Collections.reverse(mpsList);
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();


                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);
                    }
                });


                debates.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return Integer.parseInt(lhs.getDebates()) - Integer.parseInt(rhs.getDebates());
                            }
                        });
                        Collections.reverse(mpsList);
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);
                    }
                });

                bills.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return Integer.parseInt(lhs.getPrivate_bills()) - Integer.parseInt(rhs.getPrivate_bills());
                            }
                        });
                        Collections.reverse(mpsList);
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);
                    }
                });
                question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return Integer.parseInt(lhs.getQuestions()) - Integer.parseInt(rhs.getQuestions());
                            }
                        });
                        Collections.reverse(mpsList);
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);
                    }
                });

                z2a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return lhs.getLast_name().compareTo(rhs.getLast_name());
                            }
                        });
                        Collections.reverse(mpsList);
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);
                    }
                });


                a2z.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return lhs.getLast_name().compareTo(rhs.getLast_name());
                            }
                        });
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);
                    }
                });


                bottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return Integer.parseInt(lhs.getScore()) - Integer.parseInt(rhs.getScore());
                            }
                        });
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);

                    }
                });

                top.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Collections.sort(mpsList, new Comparator<MpData>() {
                            @Override
                            public int compare(MpData lhs, MpData rhs) {
                                return Integer.parseInt(lhs.getScore()) - Integer.parseInt(rhs.getScore());
                            }

                        });

                        Collections.reverse(mpsList);
                        adapter = new CustomAdapter(new_list_display.this, mpsList);

                        adapter.notifyDataSetChanged();


                        animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                        animationAdapter.notifyDataSetChanged();

                        animationAdapter.setAbsListView(list);

                        list.setAdapter(animationAdapter);


                    }
                });


                for (int i = 0; i < mpsList.size(); i++) {
                    String constit = mpsList.get(i).getConstituency();

                    String points = mpsList.get(i).getScore();
                    if (constit.equals("0"))
                        constit = "";

                    if (points.equals("0"))
                        points = "";

                    else points = points + " points";


                    name.add(mpsList.get(i).getFirst_name() + " " + mpsList.get(i).getLast_name());

                    name.get(i).replaceAll(" ", "_");


                }
                adapter = new CustomAdapter(new_list_display.this, mpsList);

                adapter.notifyDataSetChanged();


                animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                animationAdapter.notifyDataSetChanged();

                animationAdapter.setAbsListView(list);

                list.setAdapter(animationAdapter);


                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent myIntent = new Intent(new_list_display.this, mp_profile.class);
                        myIntent.putExtra("name", (mpsList.get(position).getFirst_name() + " " + mpsList.get(position).getLast_name())); //Optional parameters
                        myIntent.putExtra("debate", mpsList.get(position).getDebates()); //Optional parameters
                        myIntent.putExtra("bills", mpsList.get(position).getPrivate_bills()); //Optional parameters
                        myIntent.putExtra("questions", mpsList.get(position).getQuestions()); //Optional parameters
                        myIntent.putExtra("age", mpsList.get(position).getAge()); //Optional parameters
                        myIntent.putExtra("education", mpsList.get(position).getEducation_details()); //Optional parameters
                        myIntent.putExtra("constit", mpsList.get(position).getConstituency()); //Optional parameters
                        myIntent.putExtra("state", mpsList.get(position).getState()); //Optional parameters
                        myIntent.putExtra("house", mpsList.get(position).getHouse()); //Optional parameters
                        myIntent.putExtra("img", mpsList.get(position).getImgurl()); //Optional parameters
                        myIntent.putExtra("id", mpsList.get(position).getId()); //Optional parameters


                        new_list_display.this.startActivity(myIntent);


                    }
                });


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });



        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1) {

                if (i == R.id.bbn_item1) {

                    list.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE);
                    menu.showMenu(true);
                    list2.setVisibility(View.GONE);


                    pin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            names.setVisibility(View.VISIBLE);
                            pin.setVisibility(View.GONE);


                            search.setHint("Search for an MP");

                            search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                @Override
                                public void onFocusChange(View v, boolean hasFocus) {
                                    if (hasFocus)
                                        startActivity(new Intent(new_list_display.this, searchActivity.class));
                                }
                            });
                            search.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(new_list_display.this, searchActivity.class));
                                }
                            });


                        }
                    });


                    names.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            names.setVisibility(View.GONE);
                            pin.setVisibility(View.VISIBLE);

                            search.setHint("Search for an MP");

                            search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                @Override
                                public void onFocusChange(View v, boolean hasFocus) {
                                    if (hasFocus)
                                        startActivity(new Intent(new_list_display.this, searchActivity.class));
                                }
                            });
                            search.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(new_list_display.this, searchActivity.class));
                                }
                            });


                            search.setHint("Enter a pincode");

                            search.setOnClickListener(null);

                            search.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    if (search.getText().toString().length() >= 6) {
                                        try {
                                            String dis = new RetrieveArt().execute(search.getText().toString()).get();
                                            search.setText(dis);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });


                        }

                    });


                    SharedPreferences appSharedPrefs = PreferenceManager
                            .getDefaultSharedPreferences(new_list_display.this);

                    SQLiteDBHelper helper = new SQLiteDBHelper(new_list_display.this);
                    String session = helper.getUserDetails().getSession().getSessionid();
                    String token = helper.getUserDetails().getSession().getToken();
                    apiClient.getAPI().getMp(session, token, new Callback<MpResponse>() {
                        @Override
                        public void success(MpResponse mpResponse, Response response) {

                            mpsList = new ArrayList<MpData>();
                            name = new ArrayList<String>();

                            mpsList.addAll(mpResponse.getResponse().getData());


                            Collections.sort(mpsList, new CustomComparator());
                            Collections.reverse(mpsList);

                            attend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return Integer.parseInt(lhs.getAttendance_percentage()) - Integer.parseInt(rhs.getAttendance_percentage());
                                        }
                                    });
                                    Collections.reverse(mpsList);
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();


                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);
                                }
                            });


                            debates.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return Integer.parseInt(lhs.getDebates()) - Integer.parseInt(rhs.getDebates());
                                        }
                                    });
                                    Collections.reverse(mpsList);
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);
                                }
                            });

                            bills.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return Integer.parseInt(lhs.getPrivate_bills()) - Integer.parseInt(rhs.getPrivate_bills());
                                        }
                                    });
                                    Collections.reverse(mpsList);
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);
                                }
                            });
                            question.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return Integer.parseInt(lhs.getQuestions()) - Integer.parseInt(rhs.getQuestions());
                                        }
                                    });
                                    Collections.reverse(mpsList);
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);
                                }
                            });

                            z2a.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return lhs.getLast_name().compareTo(rhs.getLast_name());
                                        }
                                    });
                                    Collections.reverse(mpsList);
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);
                                }
                            });


                            a2z.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return lhs.getLast_name().compareTo(rhs.getLast_name());
                                        }
                                    });
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);
                                }
                            });


                            bottom.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return Integer.parseInt(lhs.getScore()) - Integer.parseInt(rhs.getScore());
                                        }
                                    });
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);

                                }
                            });

                            top.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    Collections.sort(mpsList, new Comparator<MpData>() {
                                        @Override
                                        public int compare(MpData lhs, MpData rhs) {
                                            return Integer.parseInt(lhs.getScore()) - Integer.parseInt(rhs.getScore());
                                        }

                                    });

                                    Collections.reverse(mpsList);
                                    adapter = new CustomAdapter(new_list_display.this, mpsList);

                                    adapter.notifyDataSetChanged();


                                    animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                                    animationAdapter.notifyDataSetChanged();

                                    animationAdapter.setAbsListView(list);

                                    list.setAdapter(animationAdapter);


                                }
                            });


                            for (int i = 0; i < mpsList.size(); i++) {
                                String constit = mpsList.get(i).getConstituency();

                                String points = mpsList.get(i).getScore();
                                if (constit.equals("0"))
                                    constit = "";

                                if (points.equals("0"))
                                    points = "";

                                else points = points + " points";


                                name.add(mpsList.get(i).getFirst_name() + " " + mpsList.get(i).getLast_name());

                                name.get(i).replaceAll(" ", "_");


                            }
                            adapter = new CustomAdapter(new_list_display.this, mpsList);

                            adapter.notifyDataSetChanged();


                            animationAdapter = new SwingLeftInAnimationAdapter(adapter);
                            animationAdapter.notifyDataSetChanged();

                            animationAdapter.setAbsListView(list);

                            list.setAdapter(animationAdapter);


                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent myIntent = new Intent(new_list_display.this, mp_profile.class);
                                    myIntent.putExtra("name", (mpsList.get(position).getFirst_name() + " " + mpsList.get(position).getLast_name())); //Optional parameters
                                    myIntent.putExtra("debate", mpsList.get(position).getDebates()); //Optional parameters
                                    myIntent.putExtra("bills", mpsList.get(position).getPrivate_bills()); //Optional parameters
                                    myIntent.putExtra("questions", mpsList.get(position).getQuestions()); //Optional parameters
                                    myIntent.putExtra("age", mpsList.get(position).getAge()); //Optional parameters
                                    myIntent.putExtra("education", mpsList.get(position).getEducation_details()); //Optional parameters
                                    myIntent.putExtra("constit", mpsList.get(position).getConstituency()); //Optional parameters
                                    myIntent.putExtra("state", mpsList.get(position).getState()); //Optional parameters
                                    myIntent.putExtra("house", mpsList.get(position).getHouse()); //Optional parameters
                                    myIntent.putExtra("img", mpsList.get(position).getImgurl()); //Optional parameters
                                    myIntent.putExtra("id", mpsList.get(position).getId()); //Optional parameters


                                    new_list_display.this.startActivity(myIntent);


                                }
                            });


                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });


                    imageLoader = ImageLoader.getInstance();

                }

                if (i == R.id.bbn_item2) {
                    list.setVisibility(View.GONE);
                    toolbar.setVisibility(View.GONE);
                    menu.hideMenu(true);
                    list2.setVisibility(View.VISIBLE);



                    SQLiteDBHelper helper = new SQLiteDBHelper(new_list_display.this);
                    String session = helper.getUserDetails().getSession().getSessionid();
                    String token = helper.getUserDetails().getSession().getToken();

                    apiClient.getAPI().getTags(session, token, new Callback<TagsModel>() {
                        @Override
                        public void success(TagsModel tagsModel, Response response) {
                            tags.addAll(tagsModel.getResponse().getData());
                            list2.setAdapter(new tagAdapter(new_list_display.this, tagsModel.getResponse().getData()));

                            list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Toast.makeText(new_list_display.this,tags.get(position).getMps(),Toast.LENGTH_LONG).show();
                                }
                            });

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });


                }

            }

            @Override
            public void onMenuItemReselect(@IdRes int i, int i1) {

            }
        });


    }


    public class CustomComparator implements Comparator<MpData> {
        @Override
        public int compare(MpData o1, MpData o2) {
            return Integer.valueOf(o1.getScore()).compareTo(Integer.valueOf(o2.getScore()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    class RetrieveArt extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... pincode) {
            String district = "";
            String pin1 = pincode[0];
            try {
                Log.d("PINCODE____------>", pin1);
                Uri uri = Uri.parse("http://postalpincode.in/api/pincode/" + pin1);

                HttpGet httpGet = new HttpGet(String.valueOf(uri));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                HttpClient httpClient = new DefaultHttpClient();
                String request = null;

                request = httpClient.execute(httpGet, responseHandler);

                JSONObject root = null;

                root = new JSONObject(request);
                JSONArray postoffice = root.getJSONArray("PostOffice");
                district = postoffice.getJSONObject(0).getString("District");
                Log.d("DISTRICT______------>", district);


            } catch (Exception e) {
                e.printStackTrace();
            }

            adapter.getFilter().filter(district);

            return district;
        }


        protected void onPostExecute(final String district) {
            Toast.makeText(new_list_display.this, "DISTRICT______------>" + district, Toast.LENGTH_LONG).show();

        }

    }


}
