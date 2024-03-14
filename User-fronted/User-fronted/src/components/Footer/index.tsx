import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright="Powered by Laporte"
      links={[
        {
          key: 'Laporte',
          title: 'Laporte',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/Laporte12138',
          blankTarget: true,
        },
        {
          key: 'Man City',
          title: 'Man City',
          href: 'https://www.mancitychina.com/',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
