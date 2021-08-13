#!/usr/bin/env python
# coding: utf-8

# In[1]:


def writeDB(uno):
    import pandas as pd
    import pymysql

    df = pd.read_csv('review.csv')
    df['uno'] = uno
    
    try:
        connection = pymysql.connect(
            host = "localhost",
            user = "root",
            password = "",
            database = "mydb"
        )

        cursor = connection.cursor()
        
        for i in range(len(df)):
            sql = "INSERT INTO review VALUES (NULL,%s, %s, %s, %s, %s, %s, %s)"
            cursor.execute(sql, tuple(df.iloc[i].tolist()))

            connection.commit()
            
    except Exception as e:
        print(e)

    finally:
        connection.close()


# In[ ]:




